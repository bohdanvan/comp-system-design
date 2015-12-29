package com.bvan.cs.core.util.cluster;

import com.bvan.requirements.Requirements;

/**
 * @author bvanchuhov
 */
public final class ClusterUtils {

    private ClusterUtils() {
    }

    public static int clusterId(int nodeId, int clusterNodes) {
        return nodeId / clusterNodes;
    }

    public static int clusterId(int nodeId, ClusterTopologyInfo clusterTopologyInfo) {
        return clusterId(nodeId, clusterTopologyInfo.getClusterNodes());
    }

    public static int clusterNodeId(int nodeId, int clusterNodes) {
        return nodeId % clusterNodes;
    }

    public static int clusterNodeId(int nodeId, ClusterTopologyInfo clusterTopologyInfo) {
        return clusterNodeId(nodeId, clusterTopologyInfo.getClusterNodes());
    }

    public static int generalId(int clusterId, int clusterNodeId, ClusterTopologyInfo clusterTopologyInfo) {
        Requirements.requiredCorrectIndex(clusterId, clusterTopologyInfo.getClusters(), "clusterId");
        Requirements.requiredCorrectIndex(clusterNodeId, clusterTopologyInfo.getNodes(), "clusterNodeId");

        return clusterTopologyInfo.getClusterNodes() * clusterId + clusterNodeId;
    }

    public static int firstClusterNodeId(int nodeId, int clusterNodes) {
        return (nodeId / clusterNodes) * clusterNodes;
    }
}
