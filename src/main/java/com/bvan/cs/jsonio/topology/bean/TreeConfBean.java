package com.bvan.cs.jsonio.topology.bean;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.topology.Cluster;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author bvanchuhov
 */
public class TreeConfBean {
    @SerializedName("levelIncreaseMod")
    private String levelIncreaseMod;

    @SerializedName("cluster")
    private ClusterBean clusterBean;

    @SerializedName("childrenAdjacencyMaps")
    private List<Map<Integer, Set<Integer>>> rawChildrenAdjacencyMaps;

    public String getLevelIncreaseMod() {
        return levelIncreaseMod;
    }

    public Cluster getCluster() {
        return clusterBean.toCluster();
    }

    public List<AdjacencyMap> getChildrenAdjacencyMaps() {
        List<AdjacencyMap> adjacencyMaps = new ArrayList<>(rawChildrenAdjacencyMaps.size());

        for (Map<Integer, Set<Integer>> rawChildrenAdjacencyMap : rawChildrenAdjacencyMaps) {
            adjacencyMaps.add(new AdjacencyMap(rawChildrenAdjacencyMap));
        }

        return adjacencyMaps;
    }
}
