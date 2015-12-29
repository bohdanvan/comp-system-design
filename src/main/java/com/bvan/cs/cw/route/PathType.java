package com.bvan.cs.cw.route;

/**
 * @author bvanchuhov
 */
public enum PathType {

    DIRECT(true),
    BACK(false);

    private boolean isCorrect;

    PathType(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
