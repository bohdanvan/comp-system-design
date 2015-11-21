package com.bvan.cs.core.topology.tree;

import java.util.Objects;

/**
 * @author bvanchuhov
 */
public enum LevelIncreaseMod {
    ONE_PARENT_CHILDS("one-parent-childs"),
    ALL_CHILDS("all-childs");

    public static final LevelIncreaseMod DEFAULT = ALL_CHILDS;

    private String name;

    LevelIncreaseMod(String name) {
        this.name = name;
    }

    public static LevelIncreaseMod of(String name) {
        for (LevelIncreaseMod levelIncreaseMod : LevelIncreaseMod.values()) {
            if (Objects.equals(levelIncreaseMod.name, name)) {
                return levelIncreaseMod;
            }
        }
        throw new IllegalArgumentException("LevelIncreaseMod with name + " + name + " does not exist");
    }
}
