package com.bvan.cs.cw;

import com.bvan.cs.core.util.cluster.ClusterTopologyInfo;
import com.bvan.cs.core.util.cluster.ClusterUtils;

import static com.bvan.requirements.Requirements.requiredNotNegativeArg;
import static com.bvan.requirements.Requirements.requiredNotNullArg;

/**
 * @author bvanchuhov
 */
public class ClusterNode extends Node {

    protected ClusterTopologyInfo clusterTopologyInfo;

    public static ClusterNode createByGeneralId(int nodeId, ClusterTopologyInfo clusterTopologyInfo) {
        requiredNotNegativeArg(nodeId, "nodeId");
        requiredNotNullArg(clusterTopologyInfo, "clusterTopologyInfo");

        return new ClusterNode(nodeId, clusterTopologyInfo);
    }

    public static ClusterNode createByClusterId(int clusterId, int clusterNodeId, ClusterTopologyInfo clusterTopologyInfo) {
        requiredNotNegativeArg(clusterId, "nodeId");
        requiredNotNegativeArg(clusterNodeId, "nodeId");
        requiredNotNullArg(clusterTopologyInfo, "clusterTopologyInfo");

        return new ClusterNode(clusterId, clusterNodeId, clusterTopologyInfo);
    }

    protected ClusterNode(int id, ClusterTopologyInfo clusterTopologyInfo) {
        super(id, clusterTopologyInfo.getNodes());

        this.clusterTopologyInfo = clusterTopologyInfo;
    }

    protected ClusterNode(int clusterId, int clusterNodeId, ClusterTopologyInfo clusterTopologyInfo) {
        this(ClusterUtils.generalId(clusterId, clusterNodeId, clusterTopologyInfo), clusterTopologyInfo);
    }

    public int getClusterNodeId() {
        return ClusterUtils.clusterNodeId(id, clusterTopologyInfo);
    }

    public int getClusterId() {
        return ClusterUtils.clusterId(id, clusterTopologyInfo);
    }

    public int getClusters() {
        return clusterTopologyInfo.getClusters();
    }

    public int getClusterNodes() {
        return clusterTopologyInfo.getNodes();
    }

    public ClusterTopologyInfo getClusterTopologyInfo() {
        return clusterTopologyInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClusterNode)) return false;
        if (!super.equals(o)) return false;

        ClusterNode that = (ClusterNode) o;

        return !(clusterTopologyInfo != null ? !clusterTopologyInfo.equals(that.clusterTopologyInfo) : that.clusterTopologyInfo != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (clusterTopologyInfo != null ? clusterTopologyInfo.hashCode() : 0);
        return result;
    }
}
