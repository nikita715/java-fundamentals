package javase.unit8.task2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface ConnectionPool extends Supplier<Connection>, AutoCloseable {
	
	BlockingQueue<PooledConnection> getConnectionQueue();
	
	static ConnectionPool create(String pathToConfig) {
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(pathToConfig));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Class.forName(getValueAndRemoveKey(properties, "driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = getValueAndRemoveKey(properties, "url");
		int poolSize = Integer.parseInt(getValueAndRemoveKey(properties, "poolSize"));
		
		BlockingQueue<PooledConnection> connectionQueue = new ArrayBlockingQueue<>(poolSize);
		
		for (int i = 0; i < poolSize; i++)
			try {
				connectionQueue.add(
						PooledConnection.create(
								DriverManager.getConnection(url, properties),
								connectionQueue));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return () -> connectionQueue;
	}
	
	static ConnectionPool create(String pathToConfig, String pathToInitScript) {
		
		ConnectionPool connectionPool = create(pathToConfig);
		
		try {
			try (Connection connection = connectionPool.get();
				 Statement statement = connection.createStatement()) {
				
				Arrays.stream(
						Files.lines(Paths.get(pathToInitScript))
								.collect(Collectors.joining())
								.split(";"))
						.forEachOrdered(sql -> {
							try {
								statement.addBatch(sql);
							} catch (SQLException e) {
								throw new RuntimeException();
							}
						});
				
				statement.executeBatch();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return connectionPool;
	}
	
	static String getValueAndRemoveKey(Properties properties, String key) {
		return (String) properties.remove(key);
	}
	
	@Override
	default Connection get() {
		try {
			return getConnectionQueue().take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	default void close() throws Exception {
		getConnectionQueue().forEach(connection -> {
			try {
				connection.reallyClose();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
}