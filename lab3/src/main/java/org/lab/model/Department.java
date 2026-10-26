package org.lab.model;

import java.util.ArrayList;
import java.util.List;

public class Department extends Subdivision {
    private final List<Group> groups = new ArrayList<>();

    public Department(String name, Human head) {
        super(name, head);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
}