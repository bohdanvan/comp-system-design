package com.bvan.cs.core.util.tree;

/**
 * @author bvanchuhov
 */
public final class BinTreeId {

    private final int nodeId;
    private final int nodes;

    public BinTreeId(int nodeId, int nodes) {
        this.nodeId = nodeId;
        this.nodes = nodes;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getNodes() {
        return nodes;
    }

    public int getLevel() {
        return BinTreeUtils.level(nodeId);
    }

    public int getLevelNodeId() {
        return BinTreeUtils.levelNodeId(nodeId);
    }

    public int getLevelNodes()  {
        return BinTreeUtils.levelNodes(getLevel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinTreeId)) return false;

        BinTreeId binTreeId = (BinTreeId) o;

        if (nodeId != binTreeId.nodeId) return false;
        return nodes == binTreeId.nodes;
    }

    @Override
    public int hashCode() {
        int result = nodeId;
        result = 31 * result + nodes;
        return result;
    }
}
