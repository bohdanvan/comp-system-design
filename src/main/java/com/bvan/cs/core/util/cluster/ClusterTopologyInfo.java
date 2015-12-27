package com.bvan.cs.core.util.cluster;

/**
 * @author bvanchuhov
 */
public final class ClusterTopologyInfo {

    private final int clusters;
    private final int clusterNodes;

    public ClusterTopologyInfo(int clusters, int clusterNodes) {
        this.clusters = clusters;
        this.clusterNodes = clusterNodes;
    }

    public int getClusters() {
        return clusters;
    }

    public int getClusterNodes() {
        return clusterNodes;
    }

    public int getNodes() {
        return clusters * clusterNodes;
    }
}
