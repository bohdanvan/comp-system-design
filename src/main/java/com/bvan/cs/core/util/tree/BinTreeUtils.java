package com.bvan.cs.core.util.tree;

import java.util.List;

/**
 * @author bvanchuhov
 */
public final class BinTreeUtils {

    public static final int DEGREE = 2;

    private BinTreeUtils() {
    }

    public static int level(int nodeId) {
        return TreeUtils.level(nodeId, DEGREE);
    }

    public static int levelNodes(int level) {
        return TreeUtils.levelNodes(level, DEGREE);
    }

    public static int levelNodeId(int nodeId) {
        return TreeUtils.levelNodeId(nodeId, DEGREE);
    }

    public static List<Integer> levelNodeIds(int level) {
        return TreeUtils.levelNodeIds(level, DEGREE);
    }

    public static int levelFirstNodeId(int level) {
        return TreeUtils.levelFirstNodeId(level, DEGREE);
    }

    public static int levelLastNodeId(int level) {
        return TreeUtils.levelLastNodeId(level, DEGREE);
    }

    public static List<Integer> childIds(int nodeId, int nodes) {
        return TreeUtils.childIds(nodeId, DEGREE, nodes);
    }

    public static int child(int nodeId, BinTreeChild binTreeChild) {
        return TreeUtils.child(nodeId, binTreeChild.getIndex(), DEGREE);
    }

    public static boolean hasChilds(int nodeId, int nodes) {
        return TreeUtils.hasChilds(nodeId, DEGREE, nodes);
    }

    public static boolean hasParent(int nodeId) {
        return TreeUtils.hasParent(nodeId);
    }

    public static boolean isLeftChild(int nodeId) {
        return nodeId % DEGREE == 1;
    }

    public static boolean isRightChild(int nodeId) {
        return nodeId % DEGREE == 0;
    }

    public static int parent(int nodeId) {
        if (!hasParent(nodeId)) {
            return -1;
        }
        return (nodeId - 1) / DEGREE;
    }
}
