package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public Connection connect() throws SQLException {

        String url = "jdbc:postgresql://localhost:5432/restoran_system";
        String user = "postgres";
        String password = "12345";

        return DriverManager.getConnection(url, user, password);
    }

}
