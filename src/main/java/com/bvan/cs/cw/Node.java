package com.bvan.cs.cw;

import com.bvan.requirements.Requirements;

/**
 * @author bvanchuhov
 */
public class Node {

    protected final int id;
    protected final int nodes;

    public Node(int id, int nodes) {
        this.nodes = Requirements.requiredNotNegativeArg(nodes);
        this.id = Requirements.requiredCorrectIndex(id, nodes, "id");
    }

    public int getId() {
        return id;
    }

    public int getNodes() {
        return nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (id != node.id) return false;
        return nodes == node.nodes;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nodes;
        return result;
    }
}
