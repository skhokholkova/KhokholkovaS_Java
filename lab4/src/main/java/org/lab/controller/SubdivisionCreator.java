package org.lab.controller;

import org.lab.model.Human;
import org.lab.model.Subdivision;

public abstract class SubdivisionCreator<S extends Subdivision, C> implements Creator<S> {
    private final Creator<C> childCreator;
    private final HumanCreator humanCreator;
    private final int childrenCount;
    private int counter = 0;

    protected SubdivisionCreator(Creator<C> childCreator, HumanCreator humanCreator, int childrenCount) {
        this.childCreator = childCreator;
        this.humanCreator = humanCreator;
        this.childrenCount = childrenCount;
    }

    @Override
    public S create() {
        counter++;
        S unit = createEmpty(getNamePrefix() + " " + counter, humanCreator.create());
        for (int i = 0; i < childrenCount; i++) {
            addChild(unit, childCreator.create());
        }
        return unit;
    }

    protected abstract String getNamePrefix();

    protected abstract S createEmpty(String name, Human head);

    protected abstract void addChild(S unit, C child);
}
