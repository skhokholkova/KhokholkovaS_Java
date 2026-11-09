package org.lab.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Параметри підключення до бази даних: URL, ім'я користувача та пароль. */
public class DbConfig {
    private final String url;
    private final String user;
    private final String password;

    public DbConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /** Читає параметри з файлу в resources (db.properties). */
    public static DbConfig fromResource(String resourceName) {
        try (InputStream in = DbConfig.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (in == null) {
                throw new DataAccessException("Не знайдено файл налаштувань " + resourceName, null);
            }
            Properties properties = new Properties();
            properties.load(in);
            return new DbConfig(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new DataAccessException("Не вдалося прочитати " + resourceName, e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
