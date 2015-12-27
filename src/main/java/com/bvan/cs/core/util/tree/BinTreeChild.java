package com.bvan.cs.core.util.tree;

/**
 * @author bvanchuhov
 */
public enum BinTreeChild {
    LEFT_CHILD(0),
    RIGHT_CHILD(1);

    private final int index;

    BinTreeChild(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
