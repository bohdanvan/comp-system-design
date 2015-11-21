package com.bvan.cs.core;

import com.bvan.common.Tuple;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public final class Adjacency {
    private AdjacencyMap adjacencyMap;
    private List<Tuple<Integer>> clustersPairs;

    public Adjacency(AdjacencyMap adjacencyMap, List<Tuple<Integer>> clustersPairs) {
        this.adjacencyMap = adjacencyMap;
        this.clustersPairs = clustersPairs;
    }

    public Adjacency(AdjacencyMap adjacencyMap) {
        this(adjacencyMap, new LinkedList<Tuple<Integer>>());
    }

    public Adjacency addClusterPair(Tuple<Integer> clusterPair) {
        clustersPairs.add(clusterPair);
        return this;
    }

    public Adjacency addClusterPairs(List<Tuple<Integer>> clustersPairs) {
        this.clustersPairs.addAll(clustersPairs);
        return this;
    }

    public AdjacencyMap getAdjacencyMap() {
        return adjacencyMap;
    }

    public List<Tuple<Integer>> getClustersPairs() {
        return clustersPairs;
    }
}
