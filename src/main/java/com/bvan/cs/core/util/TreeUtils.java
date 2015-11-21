package com.bvan.cs.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public final class TreeUtils {
    private TreeUtils() {}

    public static List<Integer> levelNodes(int level, int degree) {
        List<Integer> levelNodes = new ArrayList<>();

        for (int node = levelFirstNode(level, degree), lastCluster = levelLastNode(level, degree); node <= lastCluster ; node++) {
            levelNodes.add(node);
        }

        return levelNodes;
    }

    public static int levelFirstNode(int level, int degree) {
        return (int) Math.pow(degree, level) - 1;
    }

    public static int levelLastNode(int level, int degree) {
        return (int) Math.pow(degree, level + 1) - 2;
    }

    public static List<Integer> childs(int node, int degree, int nodesQuantity) {
        List<Integer> childrenClusters = new ArrayList<>();

        for (int childIndex = 0; childIndex < degree; childIndex++) {
            int childCluster = getChild(node, childIndex, degree);

            if (childCluster < nodesQuantity) {
                childrenClusters.add(childCluster);
            }
        }
        return childrenClusters;
    }

    public static int getChild(int node, int childIndex, int degree) {
        return ((node + 1) * degree + childIndex) - 1;
    }
}
