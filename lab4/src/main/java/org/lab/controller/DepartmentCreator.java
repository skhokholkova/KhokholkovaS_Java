package org.lab.controller;

import org.lab.model.Group;
import org.lab.model.Department;
import org.lab.model.Human;

public class DepartmentCreator extends SubdivisionCreator<Department, Group> {

    public DepartmentCreator(Creator<Group> childCreator, HumanCreator humanCreator, int childrenCount) {
        super(childCreator, humanCreator, childrenCount);
    }

    @Override
    protected String getNamePrefix() {
        return "Кафедра";
    }

    @Override
    protected Department createEmpty(String name, Human head) {
        return new Department(name, head);
    }

    @Override
    protected void addChild(Department unit, Group child) {
        unit.addGroup(child);
    }
}
