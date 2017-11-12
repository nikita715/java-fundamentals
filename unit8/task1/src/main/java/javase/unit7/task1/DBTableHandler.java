package javase.unit7.task1;

import java.sql.*;

public class DBTableHandler {

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite::resource:one.db");
            System.out.println("Соединение установлено.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String getStudentNameById(int id) {
        try {
            String sql = "SELECT NAME FROM STUDENTS WHERE ID = ?";
            ResultSet resultSet;
            try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addStudent(String name) {
        String sql = "INSERT INTO STUDENTS (name) VALUES (?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudent(String name) {
        String sql = "DELETE FROM STUDENTS WHERE name=(?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTable() {
        try {
            String sql = "DROP TABLE STUDENT";
            try (Statement statement = getConnection().createStatement()) {
                statement.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}