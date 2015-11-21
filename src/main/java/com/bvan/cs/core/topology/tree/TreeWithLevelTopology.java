package com.bvan.cs.core.topology.tree;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.topology.Topology;
import com.bvan.cs.core.topology.TopologyFactory;

import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeWithLevelTopology extends TreeTopology {
    protected AdjacencyMap levelAdjacencyMap;

    public TreeWithLevelTopology(Topology cluster, List<AdjacencyMap> childrenAdjacencyMaps, AdjacencyMap levelAdjacencyMap) {
        super(cluster, childrenAdjacencyMaps);
        this.levelAdjacencyMap = levelAdjacencyMap;
    }

    public TreeWithLevelTopology() {
        this.levelAdjacencyMap = TopologyFactory.simpleAdjacencyMap();
    }

    //----- Getters and Setters -----

    public AdjacencyMap getLevelAdjacencyMap() {
        return levelAdjacencyMap;
    }

    public void setLevelAdjacencyMap(AdjacencyMap levelAdjacencyMap) {
        this.levelAdjacencyMap = levelAdjacencyMap;
    }
}
