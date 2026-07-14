package ir.maktabsharif.util;

import ir.maktabsharif.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String URL = "jdbc:postgresql://localhost:5432/hw17";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "1376";
    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
