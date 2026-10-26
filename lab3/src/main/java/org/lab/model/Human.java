package org.lab.model;

public class Human {
    private String firstName;
    private String lastName;
    private String middleName;
    private Sex sex;

    public Human(String firstName, String lastName, String middleName, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName + " (" + sex.getTitle() + ")";
    }
}