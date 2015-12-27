package com.bvan.cs.core.topology;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.graph.Graph;
import com.bvan.cs.graph.GraphFactory;

/**
 * @author bvanchuhov
 */
public class Cluster extends TopologyImpl {
    private final int nodes;

    public Cluster(int nodes, AdjacencyMap adjacencyMap) {
        this.nodes = nodes;

        Graph graph = GraphFactory.create(nodes, adjacencyMap);
        setGraph(graph);
    }

    @Override
    public int getNodes() {
        return nodes;
    }

    @Override
    protected Graph buildGraph() {
        return getGraph();
    }
}
