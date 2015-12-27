package com.bvan.cs.core.topology.tree;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.connector.Connector;
import com.bvan.cs.core.topology.TopologyFactory;
import com.bvan.requirements.Requirements;

/**
 * @author bvanchuhov
 */
public class TreeWithLevelTopology extends TreeTopology {
    protected AdjacencyMap levelAdjacencyMap;
    protected Connector levelConnector;

    public TreeWithLevelTopology(Connector levelConnector) {
        super();
        this.levelAdjacencyMap = TopologyFactory.simpleAdjacencyMap();
        this.levelConnector = Requirements.requiredNotNullArg(levelConnector);
    }

    //----- Getters and Setters -----

    public AdjacencyMap getLevelAdjacencyMap() {
        return levelAdjacencyMap;
    }

    public void setLevelAdjacencyMap(AdjacencyMap levelAdjacencyMap) {
        this.levelAdjacencyMap = levelAdjacencyMap;
    }
}
