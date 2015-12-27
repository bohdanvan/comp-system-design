package com.bvan.cs.core.util.cluster;

import com.bvan.requirements.Requirements;

/**
 * @author bvanchuhov
 */
public final class ClusterUtils {

    private ClusterUtils() {
    }

    public static int clusterId(int nodeId, int clusters) {
        return nodeId / clusters;
    }

    public static int clusterId(int nodeId, ClusterTopologyInfo clusterTopologyInfo) {
        return clusterId(nodeId, clusterTopologyInfo.getClusters());
    }

    public static int clusterNodeId(int nodeId, int clusters) {
        return nodeId % clusters;
    }

    public static int clusterNodeId(int nodeId, ClusterTopologyInfo clusterTopologyInfo) {
        return clusterNodeId(nodeId, clusterTopologyInfo.getClusters());
    }

    public static int generalId(int clusterId, int clusterNodeId, ClusterTopologyInfo clusterTopologyInfo) {
        Requirements.requiredCorrectIndex(clusterId, clusterTopologyInfo.getClusters());
        Requirements.requiredCorrectIndex(clusterNodeId, clusterTopologyInfo.getNodes());

        return clusterTopologyInfo.getClusters() * clusterId + clusterNodeId;
    }
}
