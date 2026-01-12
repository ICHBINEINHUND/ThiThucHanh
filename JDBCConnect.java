package dao;

import config.IDBConfig;

import java.sql.*;

public class JDBCConnect {
    public static Connection getJDBCConnection() {
        Connection con = null;
        /* This is a demo connection url with the database management system mysql
        If your database management system is sql server, you can replace the following information
        connectionUrl = jdbc:sqlserver://localhost:1433;databaseName=mydatabase
        */
        String connectionUrl = "jdbc:mysql://localhost:3306/mydatabase";
        try {
            // If SQL Server, you must change Class.forName: com.microsoft.sqlserver.jdbc.SQLServerDriver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Where is your JDBC Driver?");
        }
        System.out.println("JDBC Driver Registered!");

        try {
            // You must change your username and password to match the information you registered on your database
            con = DriverManager.getConnection(connectionUrl, "username", "password");
        } catch (SQLException ex) {
            System.err.println("Connection Failed! Check output console");
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Close Connection fails");
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Close ResultSet fails");
        }
    }

    public static void closePreparedStatement(PreparedStatement prepare) {
        try {
            if (prepare != null) {
                prepare.close();
            }
        } catch (SQLException e) {
            System.out.println("Close PreparedStatement fails");
        }
    }
}
