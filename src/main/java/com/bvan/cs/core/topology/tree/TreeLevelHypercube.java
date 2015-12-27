package com.bvan.cs.core.topology.tree;

import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.connector.Connector;

import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeLevelHypercube extends TreeWithLevelTopology {
    public TreeLevelHypercube(Connector levelConnector) {
        super(levelConnector);
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        return super.getClusterAdjacencies();
    }
}
