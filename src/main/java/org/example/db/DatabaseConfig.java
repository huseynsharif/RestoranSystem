package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection connect() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/restaurant_system";
        String user = "postgres";
        String password = "1234";

        return DriverManager.getConnection(url, user, password);
    }

}
