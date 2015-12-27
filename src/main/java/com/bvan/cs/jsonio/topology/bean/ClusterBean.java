package com.bvan.cs.jsonio.topology.bean;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.topology.Cluster;
import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

/**
 * @author bvanchuhov
 */
public class ClusterBean {
    @SerializedName("nodes")
    private int nodes;

    @SerializedName("adjacencyMap")
    private Map<Integer, Set<Integer>> rawAdjacencyMap;

    public int getNodes() {
        return nodes;
    }

    public AdjacencyMap getAdjacencyMap() {
        return new AdjacencyMap(rawAdjacencyMap);
    }

    public Cluster toCluster() {
        return new Cluster(nodes, getAdjacencyMap());
    }
}
