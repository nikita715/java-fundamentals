package javase.unit7.task1;

import java.io.PrintStream;
import java.sql.*;

public class DBTableHandler {
	private PrintStream printStream = new PrintStream(System.out);
	
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite::resource:one.db");
			printStream.println("Соединение установлено.");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public String getStudentNameById(int id) {
		Connection connection = getConnection();
		assert connection != null;
		try {
			String sql = "SELECT NAME FROM STUDENTS WHERE ID = ?";
			ResultSet resultSet;
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				resultSet = statement.executeQuery();
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void addStudent(String name) {
		Connection connection = getConnection();
		assert connection != null;
		
		String sql = "INSERT INTO STUDENTS (name) VALUES (?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, name);
			statement.addBatch();
			statement.executeBatch();
		} catch (SQLException e) {
			printStream.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printData() {
		Connection connection = getConnection();
		assert connection != null;
		
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM STUDENTS;");
			while (rs.next()) {
				printStream.println(String.format("%d | %s", rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				assert rs != null;
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteStudent(String name) {
		Connection connection = getConnection();
		assert connection != null;
		
		String sql = "DELETE FROM STUDENTS WHERE name=(?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, name);
			statement.execute(sql);
		} catch (SQLException e) {
			printStream.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteTable() {
		Connection connection = getConnection();
		assert connection != null;
		
		try {
			String sql = "DROP TABLE STUDENTS";
			try (Statement statement = connection.createStatement()) {
				statement.execute(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		DBTableHandler dbTableHandler = new DBTableHandler();
		System.out.println(dbTableHandler.getStudentNameById(1));
		dbTableHandler.addStudent("qwexfsfd");
		dbTableHandler.printData();
	}
}