package com.bvan.cs.core.topology;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.graph.Graph;
import com.bvan.cs.graph.GraphFactory;

/**
 * @author bvanchuhov
 */
public class Cluster extends TopologyImpl {
    private final int verticesQuantity;

    public Cluster(int verticesQuantity, AdjacencyMap adjacencyMap) {
        this.verticesQuantity = verticesQuantity;

        Graph graph = GraphFactory.create(verticesQuantity, adjacencyMap);
        setGraph(graph);
    }

    @Override
    public int getVerticesQuantity() {
        return verticesQuantity;
    }

    @Override
    protected Graph buildGraph() {
        return getGraph();
    }
}
