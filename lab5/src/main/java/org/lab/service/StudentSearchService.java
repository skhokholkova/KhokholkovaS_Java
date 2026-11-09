package org.lab.service;

import org.lab.model.Student;

import java.util.List;

/** Пошук студентів за місяцем народження. Різні реалізації — різні принципи проєктування. */
public interface StudentSearchService {

    /** Короткий опис способу пошуку для меню. */
    String getDescription();

    List<Student> findByBirthMonth(int month);
}
