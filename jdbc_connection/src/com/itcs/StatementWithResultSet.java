package com.itcs;

import java.sql.*;

public class StatementWithResultSet {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3305/studentsdb";
        String username = "root";
        String pwd = "Pass10a100%";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, pwd);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM teachers";
            String createTableSQL = "CREATE TABLE teacher1 (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(255)," +
                    "last_name VARCHAR(255)," +
                    "school VARCHAR(255)," +
                    "hire_date DATE," +
                    "salary DECIMAL(10, 2))";

            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'teacher1' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}