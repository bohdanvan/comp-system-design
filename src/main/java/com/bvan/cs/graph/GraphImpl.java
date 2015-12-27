package com.bvan.cs.graph;

import com.bvan.cs.core.AdjacencyMap;
import edu.princeton.cs.algorithms.DijkstraAllPairsSP;
import edu.princeton.cs.algorithms.DirectedEdge;
import edu.princeton.cs.algorithms.EdgeWeightedDigraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public final class GraphImpl implements Graph {
    public static final int EDGE_EXISTS = 1;

    private edu.princeton.cs.algorithms.Graph graph;

    public GraphImpl(int nodes) {
        graph = new edu.princeton.cs.algorithms.Graph(nodes);
    }

    public GraphImpl(int nodes, AdjacencyMap adjacencyMap) {
        graph = new edu.princeton.cs.algorithms.Graph(nodes);

        fillGraph(nodes, adjacencyMap);
    }

    private void fillGraph(int nodes, AdjacencyMap adjacencyMap) {
        for (int start = 0; start < nodes; start++) {
            for (Integer finish : adjacencyMap.getAdjacents(start)) {
                addEdge(start, finish);
            }
        }
    }

    @Override
    public int getNodes() {
        return graph.V();
    }

    @Override
    public int getEdgesQuantity() {
        return graph.E();
    }

    @Override
    public Graph addEdge(int start, int finish) {
        if (start != finish && !hasEdge(start, finish)) {
            graph.addEdge(start, finish);
        }
        return this;
    }

    @Override
    public List<Integer> getAdjacents(int start) {
        List<Integer> adjacents = new ArrayList<>();

        Iterable<Integer> adjacentsIterable = graph.adj(start);
        for (Integer adjacent : adjacentsIterable) {
            adjacents.add(adjacent);
        }

        return adjacents;
    }

    @Override
    public boolean hasEdge(int start, int finish) {
        for (Integer adjacent : getAdjacents(start)) {
            if (adjacent == finish) return true;
        }
        return false;
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        int nodes = graph.V();
        int[][] adjacencyMatrix = new int[nodes][nodes];

        for (int startVertex = 0; startVertex < nodes; startVertex++) {
            Iterable<Integer> finishNodeIds = graph.adj(startVertex);

            for (Integer finishVertex : finishNodeIds) {
                adjacencyMatrix[startVertex][finishVertex] = EDGE_EXISTS;
            }
        }

        return adjacencyMatrix;
    }

    @Override
    public int[][] getMinDistancesMatrix() {
        return minDistancesMatrix(toEdgeWeightedDigraph(graph));
    }

    private EdgeWeightedDigraph toEdgeWeightedDigraph(edu.princeton.cs.algorithms.Graph graph) {
        int nodes = graph.V();
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(nodes);

        for (int startVertex = 0; startVertex < nodes; startVertex++) {
            Iterable<Integer> finishNodeIds = graph.adj(startVertex);

            for (Integer finishVertex : finishNodeIds) {
                digraph.addEdge(new DirectedEdge(startVertex, finishVertex, 1.));
            }
        }

        return digraph;
    }

    private int[][] minDistancesMatrix(EdgeWeightedDigraph digraph) {
        int nodes = digraph.V();
        DijkstraAllPairsSP dijkstraAllPairsSP = new DijkstraAllPairsSP(digraph);

        int[][] minDistancesMatrix = new int[nodes][nodes];
        for (int startVertex = 0; startVertex < nodes; startVertex++) {
            for (int finishVertex = startVertex + 1; finishVertex < nodes; finishVertex++) {
                int distance = (int) dijkstraAllPairsSP.dist(startVertex, finishVertex);

                minDistancesMatrix[startVertex][finishVertex] = distance;
                minDistancesMatrix[finishVertex][startVertex] = distance;
            }
        }

        return minDistancesMatrix;
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
