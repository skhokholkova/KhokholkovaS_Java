package org.lab.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Створює таблицю students і наповнює її, якщо таблиці ще немає.
 * SQL-код береться з файлу database.sql із resources.
 */
public class DatabaseInitializer {
    private static final String SCRIPT_RESOURCE = "database.sql";
    private static final String TABLE_NAME = "STUDENTS";

    private final ConnectionProvider connectionProvider;

    public DatabaseInitializer(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void initializeIfNeeded() {
        try (Connection connection = connectionProvider.getConnection()) {
            if (!tableExists(connection)) {
                try (Statement statement = connection.createStatement()) {
                    statement.execute(readScript());
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Не вдалося ініціалізувати базу даних", e);
        }
    }

    private boolean tableExists(Connection connection) throws SQLException {
        try (ResultSet tables = connection.getMetaData().getTables(null, null, TABLE_NAME, null)) {
            return tables.next();
        }
    }

    private String readScript() {
        try (InputStream in = DatabaseInitializer.class.getClassLoader().getResourceAsStream(SCRIPT_RESOURCE)) {
            if (in == null) {
                throw new DataAccessException("Не знайдено файл " + SCRIPT_RESOURCE, null);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataAccessException("Не вдалося прочитати " + SCRIPT_RESOURCE, e);
        }
    }
}
