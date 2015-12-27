package com.bvan.cs.core.topology;

import com.bvan.cs.graph.Graph;

/**
 * @author bvanchuhov
 */
public interface Topology {
    int getNodes();
    int[][] getAdjacencyMatrix();
    int[][] getMinDistancesMatrix();
    Graph getGraph();
    TopologyProperties getProperties();
}
