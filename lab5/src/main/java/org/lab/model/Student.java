package org.lab.model;

import java.time.LocalDate;
import java.util.Objects;

/** Студент: запис таблиці students. Об'єкт незмінний (immutable). */
public class Student {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final LocalDate birthDate;
    private final String recordBookNumber;

    public Student(int id, String lastName, String firstName, String middleName,
                   LocalDate birthDate, String recordBookNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.recordBookNumber = recordBookNumber;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(middleName, student.middleName)
                && Objects.equals(birthDate, student.birthDate)
                && Objects.equals(recordBookNumber, student.recordBookNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, middleName, birthDate, recordBookNumber);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName + ", " + birthDate
                + ", залікова книжка " + recordBookNumber;
    }
}
