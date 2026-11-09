package org.lab.dao;

import org.lab.model.Student;

import java.util.List;

/** Доступ до даних про студентів. */
public interface StudentDao {

    /** Усі студенти з таблиці. */
    List<Student> findAll();

    /** Студенти, народжені в заданому місяці; відбір виконує сервер бази даних. */
    List<Student> findByBirthMonth(int month);
}
