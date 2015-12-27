package com.bvan.cs.core.topology.tree;

import com.bvan.common.Tuple;
import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.connector.Connector;
import com.bvan.cs.core.util.tree.TreeUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeLevelLinearTopology extends TreeWithLevelTopology {
    public TreeLevelLinearTopology(Connector levelConnector) {
        super(levelConnector);
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        List<Adjacency> clusterAdjacencies = super.getClusterAdjacencies();

        List<Tuple<Integer>> adjustedClusters = new LinkedList<>();
        int treeDegree = getTreeDegree();
        for (int level = getLevel(); level >= 0; level--) {
            int levelFirstNode = TreeUtils.levelFirstNodeId(level, treeDegree);
            int levelLastNode = TreeUtils.levelLastNodeId(level, treeDegree);

            List<Tuple<Integer>> levelAdjustedClusters = levelConnector.connectPairs(levelFirstNode, levelLastNode);
            adjustedClusters.addAll(levelAdjustedClusters);
        }

        clusterAdjacencies.add(new Adjacency(levelAdjacencyMap, adjustedClusters));

        return clusterAdjacencies;
    }
}
