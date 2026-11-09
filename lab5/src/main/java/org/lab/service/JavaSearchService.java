package org.lab.service;

import org.lab.dao.StudentDao;
import org.lab.model.Student;
import org.lab.util.Months;

import java.util.Comparator;
import java.util.List;

/** Принцип 1: з бази завжди береться весь список, відбір виконується в Java. */
public class JavaSearchService implements StudentSearchService {
    private final StudentDao studentDao;

    public JavaSearchService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public String getDescription() {
        return "вибір усіх студентів і фільтрація в Java";
    }

    @Override
    public List<Student> findByBirthMonth(int month) {
        Months.requireValid(month);
        return studentDao.findAll().stream()
                .filter(student -> student.getBirthDate().getMonthValue() == month)
                .sorted(Comparator
                        .comparingInt((Student student) -> student.getBirthDate().getDayOfMonth())
                        .thenComparing(Student::getLastName))
                .toList();
    }
}
