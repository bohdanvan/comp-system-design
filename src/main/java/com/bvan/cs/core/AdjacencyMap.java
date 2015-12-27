package com.bvan.cs.core;

import java.util.*;

/**
 * @author bvanchuhov
 */
public final class AdjacencyMap {
    private Map<Integer, Set<Integer>> map;

    public AdjacencyMap() {
        this(new HashMap<Integer, Set<Integer>>());
    }

    public AdjacencyMap(Map<Integer, Set<Integer>> map) {
        this.map = map;
    }

    public Set<Integer> getAdjacents(int start) {
        if (!map.containsKey(start)) {
            return new HashSet<>();
        }
        return map.get(start);
    }

    public AdjacencyMap addEdge(int start, int finish) {
        strictAddEdge(start, finish);
        strictAddEdge(finish, start);

        return this;
    }

    private void strictAddEdge(int start, int end) {
        Set<Integer> adjacentNodeIds = getAdjacents(start);
        adjacentNodeIds.add(end);
        map.put(start, adjacentNodeIds);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
