package com.itcs;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL JDBC driver. Make sure the driver JAR is in the classpath.");
            e.printStackTrace();
        }
    }
}