package org.lab.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.lab.Run;
import org.lab.model.University;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class JsonManagerTest {

    private final JsonManager jsonManager = new JsonManager();

    @TempDir
    Path tempDir;

    @Test
    void universityWrittenToFileAndReadBackIsEqual() throws IOException {
        // в кожному підрозділі по два підрозділи нижчого рівня
        University oldUniversity = Run.createTypycalUniversity();
        Path file = tempDir.resolve("university.json");

        jsonManager.writeToFile(oldUniversity, file);
        University newUniversity = jsonManager.readFromFile(file);

        assertNotSame(oldUniversity, newUniversity);
        assertTrue(oldUniversity.equals(newUniversity));
        assertEquals(oldUniversity, newUniversity);
        assertEquals(oldUniversity.hashCode(), newUniversity.hashCode());
    }

    @Test
    void universityConvertedToJsonStringAndBackIsEqual() {
        University oldUniversity = Run.createTypycalUniversity();

        String json = jsonManager.toJson(oldUniversity);
        University newUniversity = jsonManager.fromJson(json);

        assertEquals(oldUniversity, newUniversity);
    }

    @Test
    void restoredUniversityHasExpectedStructure() {
        University oldUniversity = Run.createTypycalUniversity();
        University newUniversity = jsonManager.fromJson(jsonManager.toJson(oldUniversity));

        assertEquals(2, newUniversity.getFaculties().size());
        var faculty = newUniversity.getFaculties().get(0);
        assertEquals(2, faculty.getDepartments().size());
        var department = faculty.getDepartments().get(0);
        assertEquals(2, department.getGroups().size());
        assertEquals(2, department.getGroups().get(0).getStudents().size());
    }

    @Test
    void changedUniversityIsNotEqual() {
        University oldUniversity = Run.createTypycalUniversity();
        University newUniversity = jsonManager.fromJson(jsonManager.toJson(oldUniversity));

        newUniversity.getFaculties().get(0).getDepartments().get(0)
                .getGroups().get(0).getStudents().get(0).setFirstName("Інше");

        assertNotEquals(oldUniversity, newUniversity);
    }
}