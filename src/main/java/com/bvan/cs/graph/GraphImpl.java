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

    public GraphImpl(int verticesQuantity) {
        graph = new edu.princeton.cs.algorithms.Graph(verticesQuantity);
    }

    public GraphImpl(int verticesQuantity, AdjacencyMap adjacencyMap) {
        graph = new edu.princeton.cs.algorithms.Graph(verticesQuantity);

        fillGraph(verticesQuantity, adjacencyMap);
    }

    private void fillGraph(int verticesQuantity, AdjacencyMap adjacencyMap) {
        for (int start = 0; start < verticesQuantity; start++) {
            for (Integer finish : adjacencyMap.getAdjacents(start)) {
                addEdge(start, finish);
            }
        }
    }

    @Override
    public int getVerticesQuantity() {
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
        int verticesQuantity = graph.V();
        int[][] adjacencyMatrix = new int[verticesQuantity][verticesQuantity];

        for (int startVertex = 0; startVertex < verticesQuantity; startVertex++) {
            Iterable<Integer> finishVertices = graph.adj(startVertex);

            for (Integer finishVertex : finishVertices) {
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
        int verticesQuantity = graph.V();
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(verticesQuantity);

        for (int startVertex = 0; startVertex < verticesQuantity; startVertex++) {
            Iterable<Integer> finishVertices = graph.adj(startVertex);

            for (Integer finishVertex : finishVertices) {
                digraph.addEdge(new DirectedEdge(startVertex, finishVertex, 1.));
            }
        }

        return digraph;
    }

    private int[][] minDistancesMatrix(EdgeWeightedDigraph digraph) {
        int verticesQuantity = digraph.V();
        DijkstraAllPairsSP dijkstraAllPairsSP = new DijkstraAllPairsSP(digraph);

        int[][] minDistancesMatrix = new int[verticesQuantity][verticesQuantity];
        for (int startVertex = 0; startVertex < verticesQuantity; startVertex++) {
            for (int finishVertex = startVertex + 1; finishVertex < verticesQuantity; finishVertex++) {
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
