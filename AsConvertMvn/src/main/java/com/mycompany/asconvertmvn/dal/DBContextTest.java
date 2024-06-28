package com.mycompany.asconvertmvn.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContextTest {
    public static String password = "1234568";

    public DBContextTest() {
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        String username = "sa";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Store_Assignment3;encrypt=false";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
    }

}
