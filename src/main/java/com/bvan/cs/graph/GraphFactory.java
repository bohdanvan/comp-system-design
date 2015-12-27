package com.bvan.cs.graph;

import com.bvan.cs.core.AdjacencyMap;

/**
 * @author bvanchuhov
 */
public final class GraphFactory {
    private GraphFactory() {}

    public static Graph create(int nodes, AdjacencyMap adjacencyMap) {
        return new GraphImpl(nodes, adjacencyMap);
    }

    public static Graph create(int nodes) {
        return new GraphImpl(nodes);
    }
}
