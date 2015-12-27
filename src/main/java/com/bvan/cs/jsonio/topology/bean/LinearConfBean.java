package com.bvan.cs.jsonio.topology.bean;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.topology.Cluster;
import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

/**
 * @author bvanchuhov
 */
public class LinearConfBean {
    @SerializedName("cluster")
    protected ClusterBean clusterBean;

    @SerializedName("clusterAdjacencyMap")
    protected Map<Integer, Set<Integer>> rawClusterAdjacencyMap;

    public Cluster getCluster() {
        return clusterBean.toCluster();
    }

    public AdjacencyMap getClusterAdjacencyMap() {
        return new AdjacencyMap(rawClusterAdjacencyMap);
    }
}
