package com.bvan.cs.graph;

import java.util.List;

/**
 * @author bvanchuhov
 */
public interface Graph {
    int getVerticesQuantity();
    int getEdgesQuantity();

    Graph addEdge(int start, int finish);
    List<Integer> getAdjacents(int start);

    boolean hasEdge(int start, int finish);

    int[][] getAdjacencyMatrix();
    int[][] getMinDistancesMatrix();
}
