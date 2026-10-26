package org.lab.controller;

import org.lab.model.Faculty;
import org.lab.model.University;
import org.lab.model.Human;

public class UniversityCreator extends SubdivisionCreator<University, Faculty> {

    public UniversityCreator(Creator<Faculty> childCreator, HumanCreator humanCreator, int childrenCount) {
        super(childCreator, humanCreator, childrenCount);
    }

    @Override
    protected String getNamePrefix() {
        return "Університет";
    }

    @Override
    protected University createEmpty(String name, Human head) {
        return new University(name, head);
    }

    @Override
    protected void addChild(University unit, Faculty child) {
        unit.addFaculty(child);
    }
}