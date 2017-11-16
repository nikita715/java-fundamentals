package javase.unit8.task2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Supplier;

public class ConnectionPoolTest {
	
	public static final String PATH_TO_CONFIG = Paths.get("src/test/resources/db.properties").toAbsolutePath().toString();
	public static final String PATH_TO_INIT = Paths.get("src/test/resources/init.sql").toAbsolutePath().toString();
	static Supplier<Connection> connectionPool;
	
	@BeforeClass
	public static void init() throws SQLException, IOException {
		connectionPool = ConnectionPool.create(PATH_TO_CONFIG, PATH_TO_INIT);
	}
	
	@Test
	public void get() throws Exception {
		Collection<Book> books;
		try (Connection connection = connectionPool.get();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(
					 "SELECT * FROM Books")) {
			books = new HashSet<>();
			while (resultSet.next())
				books.add(
						new Book(
								resultSet.getLong("id"),
								resultSet.getString("name")));
		}
		
		Assert.assertTrue(books.contains(
				new Book(1L, "The Selfish Gene")
		));
		
		books.forEach(b -> System.out.printf("%d %s%n",b.getId(), b.getName()));
	}
}