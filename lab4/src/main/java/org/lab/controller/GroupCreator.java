package org.lab.controller;

import org.lab.model.Student;
import org.lab.model.Group;
import org.lab.model.Human;

public class GroupCreator extends SubdivisionCreator<Group, Student> {

    public GroupCreator(Creator<Student> childCreator, HumanCreator humanCreator, int childrenCount) {
        super(childCreator, humanCreator, childrenCount);
    }

    @Override
    protected String getNamePrefix() {
        return "Група";
    }

    @Override
    protected Group createEmpty(String name, Human head) {
        return new Group(name, head);
    }

    @Override
    protected void addChild(Group unit, Student child) {
        unit.addStudent(child);
    }
}
