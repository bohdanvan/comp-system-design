package com.bvan.cs.core.topology.linear;

import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.AdjacencyMap;
import com.bvan.common.Tuple;
import com.bvan.cs.core.adjaster.Adjaster;
import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.Topology;
import com.bvan.cs.core.topology.TopologyFactory;

import java.util.Arrays;
import java.util.List;

import static com.bvan.requirements.Requirements.requiredNotNegativeArg;
import static com.bvan.requirements.Requirements.requiredNotNullArg;

/**
 * @author bvanchuhov
 */
public class LinearTopology extends ClusterBasedTopology {
    private static int DEFAULT_CLUSTERS_QUANTITY = 10;

    protected int clustersQuantity = DEFAULT_CLUSTERS_QUANTITY;
    protected AdjacencyMap clusterAdjacencyMap;

    protected Adjaster adjaster;

    public LinearTopology(Topology cluster, AdjacencyMap clusterAdjacencyMap, Adjaster adjaster) {
        super(cluster);
        this.clusterAdjacencyMap = requiredNotNullArg(clusterAdjacencyMap);
        this.adjaster = requiredNotNullArg(adjaster);
    }

    public LinearTopology(Adjaster adjaster) {
        this(TopologyFactory.simpleCluster(),  TopologyFactory.simpleAdjacencyMap(), adjaster);
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        List<Tuple<Integer>> adjustedClusters = adjaster.adjustPairs(0, clustersQuantity);

        return Arrays.asList(new Adjacency(clusterAdjacencyMap, adjustedClusters));
    }

    @Override
    public int getLevel() {
        return clustersQuantity - 1;
    }

    @Override
    protected void setLevel0(int level) {
        clustersQuantity = level + 1;
    }

    @Override
    public int getClustersQuantity() {
        return clustersQuantity;
    }

    //----- Getters and Setters -----

    public AdjacencyMap getClusterAdjacencyMap() {
        return clusterAdjacencyMap;
    }

    public void setClusterAdjacencyMap(AdjacencyMap clusterAdjacencyMap) {
        this.clusterAdjacencyMap = requiredNotNullArg(clusterAdjacencyMap);
        clearCache();
    }
}
