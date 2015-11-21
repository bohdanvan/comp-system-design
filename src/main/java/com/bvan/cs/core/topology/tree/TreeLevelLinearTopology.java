package com.bvan.cs.core.topology.tree;

import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.adjaster.Adjaster;
import com.bvan.cs.core.topology.Topology;
import com.bvan.common.Tuple;
import com.bvan.cs.core.util.TreeUtils;
import com.bvan.requirements.Requirements;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeLevelLinearTopology extends TreeWithLevelTopology {
    protected Adjaster levelAdjaster;

    public TreeLevelLinearTopology(Topology cluster, List<AdjacencyMap> childrenAdjacencyMaps, AdjacencyMap levelAdjacencyMap, Adjaster levelAdjaster) {
        super(cluster, childrenAdjacencyMaps, levelAdjacencyMap);
        this.levelAdjaster = Requirements.requiredNotNullArg(levelAdjaster);
    }

    public TreeLevelLinearTopology(Adjaster levelAdjaster) {
        super();
        this.levelAdjaster = Requirements.requiredNotNullArg(levelAdjaster);
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        List<Adjacency> clusterAdjacencies = super.getClusterAdjacencies();

        List<Tuple<Integer>> adjustedClusters = new LinkedList<>();
        int treeDegree = getTreeDegree();
        for (int level = getLevel(); level >= 0; level--) {
            int levelFirstNode = TreeUtils.levelFirstNode(level, treeDegree);
            int levelLastNode = TreeUtils.levelLastNode(level, treeDegree);

            List<Tuple<Integer>> levelAdjustedClusters = levelAdjaster.adjustPairs(levelFirstNode, levelLastNode + 1);
            adjustedClusters.addAll(levelAdjustedClusters);
        }

        clusterAdjacencies.add(new Adjacency(levelAdjacencyMap, adjustedClusters));

        return clusterAdjacencies;
    }
}
