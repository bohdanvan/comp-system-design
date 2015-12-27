package com.bvan.cs.core.topology.linear;

import com.bvan.common.Tuple;
import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.connector.Connector;
import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.Topology;
import com.bvan.cs.core.topology.TopologyFactory;

import java.util.Arrays;
import java.util.List;

import static com.bvan.requirements.Requirements.requiredNotNullArg;

/**
 * @author bvanchuhov
 */
public class LinearTopology extends ClusterBasedTopology {
    private static int DEFAULT_CLUSTERS_QUANTITY = 10;

    protected int clustersQuantity = DEFAULT_CLUSTERS_QUANTITY;
    protected AdjacencyMap clusterAdjacencyMap;

    protected Connector adjaster;

    public LinearTopology(Topology cluster, AdjacencyMap clusterAdjacencyMap, Connector adjaster) {
        super(cluster);
        this.clusterAdjacencyMap = requiredNotNullArg(clusterAdjacencyMap);
        this.adjaster = requiredNotNullArg(adjaster);
    }

    public LinearTopology(Connector adjaster) {
        this(TopologyFactory.simpleCluster(),  TopologyFactory.simpleAdjacencyMap(), adjaster);
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        List<Tuple<Integer>> adjustedClusters = adjaster.connectPairs(0, clustersQuantity - 1);

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
    public int getClusters() {
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
