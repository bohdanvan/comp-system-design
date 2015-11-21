package com.bvan.cs.jsonio.topology.bean;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.topology.Cluster;
import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

/**
 * @author bvanchuhov
 */
public class GridConfBean {
    @SerializedName("cluster")
    protected ClusterBean clusterBean;

    @SerializedName("rowAdjacencyMap")
    protected Map<Integer, Set<Integer>> rawRowClusterAdjacencyMap;

    @SerializedName("colAdjacencyMap")
    protected Map<Integer, Set<Integer>> rawColClusterAdjacencyMap;

    public Cluster getCluster() {
        return clusterBean.toCluster();
    }

    public AdjacencyMap getRowClusterAdjacencyMap() {
        return new AdjacencyMap(rawRowClusterAdjacencyMap);
    }

    public AdjacencyMap getColClusterAdjacencyMap() {
        return new AdjacencyMap(rawColClusterAdjacencyMap);
    }
}
