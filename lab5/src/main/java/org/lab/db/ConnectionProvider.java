package org.lab.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Відповідає лише за створення з'єднань з базою даних через JDBC. */
public class ConnectionProvider {
    private final DbConfig config;

    public ConnectionProvider(DbConfig config) {
        this.config = config;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
    }
}
