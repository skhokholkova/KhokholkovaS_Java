package org.lab.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.lab.model.University;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Перетворює університет у JSON і назад, а також записує/читає JSON-файли.
 */
public class JsonManager {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /** University -> текст JSON. */
    public String toJson(University university) {
        return gson.toJson(university);
    }

    /** Текст JSON -> University. */
    public University fromJson(String json) {
        return gson.fromJson(json, University.class);
    }

    /** Записує університет у файл у форматі JSON (UTF-8). */
    public void writeToFile(University university, Path file) throws IOException {
        try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            gson.toJson(university, writer);
        }
    }

    /** Зчитує файл JSON і відновлює університет. */
    public University readFromFile(Path file) throws IOException {
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, University.class);
        }
    }
}