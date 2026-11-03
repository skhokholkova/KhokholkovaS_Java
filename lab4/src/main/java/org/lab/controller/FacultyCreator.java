package org.lab.controller;

import org.lab.model.Department;
import org.lab.model.Faculty;
import org.lab.model.Human;

public class FacultyCreator extends SubdivisionCreator<Faculty, Department> {

    public FacultyCreator(Creator<Department> childCreator, HumanCreator humanCreator, int childrenCount) {
        super(childCreator, humanCreator, childrenCount);
    }

    @Override
    protected String getNamePrefix() {
        return "Факультет";
    }

    @Override
    protected Faculty createEmpty(String name, Human head) {
        return new Faculty(name, head);
    }

    @Override
    protected void addChild(Faculty unit, Department child) {
        unit.addDepartment(child);
    }
}