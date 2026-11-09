package org.lab.db;

/** Помилка роботи з базою даних (обгортка над SQLException та помилками читання ресурсів). */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
