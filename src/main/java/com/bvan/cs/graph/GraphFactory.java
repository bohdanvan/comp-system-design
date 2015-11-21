package com.bvan.cs.graph;

import com.bvan.cs.core.AdjacencyMap;

/**
 * @author bvanchuhov
 */
public final class GraphFactory {
    private GraphFactory() {}

    public static Graph create(int verticesQuantity, AdjacencyMap adjacencyMap) {
        return new GraphImpl(verticesQuantity, adjacencyMap);
    }

    public static Graph create(int verticesQuantity) {
        return new GraphImpl(verticesQuantity);
    }
}
