package org.lab.service;

import org.lab.dao.StudentDao;
import org.lab.model.Student;
import org.lab.util.Months;

import java.util.List;

/** Принцип 2: SQL-запит формується під запит користувача, відбір робить СУБД. */
public class DatabaseSearchService implements StudentSearchService {
    private final StudentDao studentDao;

    public DatabaseSearchService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public String getDescription() {
        return "SQL-запит з умовою за місяцем (відбір виконує СУБД)";
    }

    @Override
    public List<Student> findByBirthMonth(int month) {
        Months.requireValid(month);
        return studentDao.findByBirthMonth(month);
    }
}
