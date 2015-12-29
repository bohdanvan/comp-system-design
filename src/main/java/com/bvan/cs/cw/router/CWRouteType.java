package com.bvan.cs.cw.router;

/**
 * @author bvanchuhov
 */
public enum CWRouteType {

    TREE(0),
    HYPERCUBE(1),
    CLUSTER(2);

    private final int priority;

    public static CWRouteType valueOf(int id) {
        for (CWRouteType cwRouteType : CWRouteType.values()) {
            if (cwRouteType.getPriority() == id) {
                return cwRouteType;
            }
        }

        throw new IllegalArgumentException("no CWRouteType with id = " + id);
    }

    public static CWRouteType highestPriority() {
        return TREE;
    }

    CWRouteType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public CWRouteType next() {
        int values = values().length;
        int nextId = (priority + 1) % values;

        return valueOf(nextId);
    }
}
