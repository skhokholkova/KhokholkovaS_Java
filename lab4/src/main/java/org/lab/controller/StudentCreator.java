package org.lab.controller;

import org.lab.model.Human;
import org.lab.model.Student;

public class StudentCreator implements Creator<Student> {
    private final HumanCreator humanCreator;

    public StudentCreator(HumanCreator humanCreator) {
        this.humanCreator = humanCreator;
    }

    @Override
    public Student create() {
        Human human = humanCreator.create();
        return new Student(human.getFirstName(), human.getLastName(),
                human.getMiddleName(), human.getSex());
    }
}