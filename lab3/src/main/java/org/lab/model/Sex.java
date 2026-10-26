package org.lab.model;

public enum Sex {
    MALE("чоловіча"),
    FEMALE("жіноча");

    private final String title;

    Sex(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}