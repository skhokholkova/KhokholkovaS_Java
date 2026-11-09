package org.lab.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lab.dao.JdbcStudentDao;
import org.lab.dao.StudentDao;
import org.lab.db.ConnectionProvider;
import org.lab.db.DatabaseInitializer;
import org.lab.db.DbConfig;
import org.lab.model.Student;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class StudentSearchServiceTest {
    private static StudentDao dao;
    private static StudentSearchService sqlService;
    private static StudentSearchService javaService;

    @BeforeAll
    static void setUp() {
        // окрема БД в пам'яті, щоб тести не чіпали справжню базу
        ConnectionProvider provider = new ConnectionProvider(
                new DbConfig("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", ""));
        DatabaseInitializer initializer = new DatabaseInitializer(provider);
        initializer.initializeIfNeeded();
        initializer.initializeIfNeeded(); // повторний виклик не має дублювати дані
        dao = new JdbcStudentDao(provider);
        sqlService = new DatabaseSearchService(dao);
        javaService = new JavaSearchService(dao);
    }

    @Test
    void tableContainsAtLeastTwentyStudents() {
        assertEquals(24, dao.findAll().size());
    }

    @Test
    void noOneIsBornInJune() {
        assertTrue(sqlService.findByBirthMonth(6).isEmpty());
        assertTrue(javaService.findByBirthMonth(6).isEmpty());
    }

    @Test
    void bothApproachesGiveTheSameResultForEveryMonth() {
        for (int month = 1; month <= 12; month++) {
            assertEquals(sqlService.findByBirthMonth(month), javaService.findByBirthMonth(month),
                    "Різні результати для місяця " + month);
        }
    }

    @Test
    void marchHasThreeStudentsAllBornInMarch() {
        List<Student> students = sqlService.findByBirthMonth(3);
        assertEquals(3, students.size());
        assertTrue(students.stream().allMatch(s -> s.getBirthDate().getMonthValue() == 3));
    }

    @Test
    void everyOtherMonthHasStudents() {
        for (int month = 1; month <= 12; month++) {
            if (month != 6) {
                assertFalse(sqlService.findByBirthMonth(month).isEmpty(), "Немає студентів у місяці " + month);
            }
        }
    }

    @Test
    void invalidMonthIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> sqlService.findByBirthMonth(0));
        assertThrows(IllegalArgumentException.class, () -> javaService.findByBirthMonth(13));
    }
}
