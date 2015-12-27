package com.bvan.cs.core.util.tree;

import java.util.ArrayList;
import java.util.List;

import static com.bvan.requirements.Requirements.requiredCorrectIndex;
import static com.bvan.requirements.Requirements.requiredNotNegativeArg;
import static com.bvan.requirements.Requirements.requiredPositiveArg;

/**
 * @author bvanchuhov
 */
public final class TreeUtils {

    private TreeUtils() {
    }

    public static int level(int nodeId, int degree) {
        requiredNotNegativeArg(nodeId, "nodeId");
        requiredPositiveArg(degree, "degree");

        int level = -1;
        int levelNodes = 1;

        while (nodeId >= 0) {
            nodeId -= levelNodes;
            levelNodes *= degree;

            level++;
        }

        return level;
    }

    public static int levelNodes(int level, int degree) {
        requiredNotNegativeArg(level, "level");
        requiredPositiveArg(degree, "degree");

        return (int) Math.pow(degree, level);
    }

    public static int levelNodeId(int nodeId, int degree) {
        requiredNotNegativeArg(nodeId, "level");
        requiredPositiveArg(degree, "degree");

        int level = level(nodeId, degree);
        int levelFirstNodeId = levelFirstNodeId(level, degree);

        return levelFirstNodeId - nodeId;
    }

    public static List<Integer> levelNodeIds(int level, int degree) {
        requiredNotNegativeArg(level, "level");
        requiredPositiveArg(degree, "degree");

        List<Integer> levelNodes = new ArrayList<>();

        for (int node = levelFirstNodeId(level, degree), lastCluster = levelLastNodeId(level, degree); node <= lastCluster; node++) {
            levelNodes.add(node);
        }

        return levelNodes;
    }

    public static int levelFirstNodeId(int level, int degree) {
        requiredNotNegativeArg(level, "level");
        requiredPositiveArg(degree, "degree");

        return (int) Math.pow(degree, level) - 1;
    }

    public static int levelLastNodeId(int level, int degree) {
        requiredNotNegativeArg(level, "level");
        requiredPositiveArg(degree, "degree");

        return (int) Math.pow(degree, level + 1) - 2;
    }

    public static List<Integer> childIds(int nodeId, int degree, int nodes) {
        requiredNotNegativeArg(nodes, "nodes");
        requiredCorrectIndex(nodeId, nodes, "nodeId");
        requiredPositiveArg(degree, "degree");

        List<Integer> childrenClusters = new ArrayList<>();

        for (int childIndex = 0; childIndex < degree; childIndex++) {
            int childCluster = child(nodeId, childIndex, degree);

            if (childCluster < nodes) {
                childrenClusters.add(childCluster);
            }
        }
        return childrenClusters;
    }

    public static int child(int nodeId, int childId, int degree) {
        requiredNotNegativeArg(nodeId, "nodeId");
        requiredNotNegativeArg(childId, "childId");
        requiredPositiveArg(degree, "degree");

        return ((nodeId + 1) * degree + childId) - 1;
    }
}
