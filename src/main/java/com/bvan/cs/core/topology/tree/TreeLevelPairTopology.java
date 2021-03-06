package com.bvan.cs.core.topology.tree;

import com.bvan.common.Tuple;
import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.connector.Connector;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeLevelPairTopology extends TreeWithLevelTopology {
    public TreeLevelPairTopology(Connector pairConnector) {
        super(pairConnector);
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        checkTreeDegree();

        List<Adjacency> clusterAdjacencies = super.getClusterAdjacencies();

        List<Tuple<Integer>> adjustedClusters = new LinkedList<>();
        for (int clusterA = 1, last = getClusters() - 1; clusterA < last; clusterA += 2) {
            adjustedClusters.addAll(levelConnector.connectPairs(clusterA, clusterA + 1));
        }

        clusterAdjacencies.add(new Adjacency(levelAdjacencyMap, adjustedClusters));

        return clusterAdjacencies;
    }

    private void checkTreeDegree() {
        if (getTreeDegree() != 2) {
            throw new IllegalStateException("degree should be 2, but actually is " + getTreeDegree());
        }
    }
}
