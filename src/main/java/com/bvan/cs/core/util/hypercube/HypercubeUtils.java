package com.bvan.cs.core.util.hypercube;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.bvan.requirements.Requirements.requiredNotNegativeArg;
import static com.bvan.requirements.Requirements.requiredPositiveArg;

/**
 * @author bvanchuhov
 */
public final class HypercubeUtils {

    public static final int RADIX = 2;

    private HypercubeUtils() {
    }

    public static int degree(int nodes) {
        requiredPositiveArg(nodes, "nodes");

        return (int) Math.round(Math.log(nodes) / Math.log(RADIX));
    }

    public static int nodes(int degree) {
        requiredNotNegativeArg(degree, "hypercubeDegree");

        return (int) Math.pow(RADIX, degree);
    }

    public static boolean isAllNodes(int nodes, int degree) {
        requiredPositiveArg(nodes, "nodes");
        requiredNotNegativeArg(degree, "degree");

        return nodes == nodes(degree);
    }

    public static boolean isAllNodes(int nodes) {
        return isAllNodes(nodes, degree(nodes));
    }

    public static List<Integer> connectedNodes(int nodeId, int degree) {
        requiredNotNegativeArg(nodeId, "nodeId");
        requiredNotNegativeArg(degree, "degree");

        List<Integer> connectedNodes = new ArrayList<>();

        BigInteger binNodeId = BigInteger.valueOf(nodeId);
        for (int i = 0; i < degree; i++) {
            connectedNodes.add(binNodeId.flipBit(i).intValue());
        }

        return connectedNodes;
    }
}
