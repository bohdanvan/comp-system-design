package com.bvan.cs.core.topology.tree;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.adjaster.Adjaster;
import com.bvan.cs.core.topology.TopologyFactory;
import com.bvan.requirements.Requirements;

/**
 * @author bvanchuhov
 */
public class TreeWithLevelTopology extends TreeTopology {
    protected AdjacencyMap levelAdjacencyMap;
    protected Adjaster levelAdjaster;

    public TreeWithLevelTopology(Adjaster levelAdjaster) {
        super();
        this.levelAdjacencyMap = TopologyFactory.simpleAdjacencyMap();
        this.levelAdjaster = Requirements.requiredNotNullArg(levelAdjaster);
    }

    //----- Getters and Setters -----

    public AdjacencyMap getLevelAdjacencyMap() {
        return levelAdjacencyMap;
    }

    public void setLevelAdjacencyMap(AdjacencyMap levelAdjacencyMap) {
        this.levelAdjacencyMap = levelAdjacencyMap;
    }
}
