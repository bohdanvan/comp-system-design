package com.bvan.cs.cw;

import com.bvan.cs.core.util.cluster.ClusterTopologyInfo;
import com.bvan.cs.core.util.tree.BinTreeId;
import com.bvan.cs.cw.router.CWClusterNodeId;

/**
 * @author bvanchuhov
 */
public class CWNode extends ClusterNode {

    public static CWNode createByGeneralId(int nodeId, ClusterTopologyInfo clusterTopologyInfo) {
        return new CWNode(nodeId, clusterTopologyInfo);
    }

    public static CWNode createByClusterId(int clusterId, int clusterNodeId, ClusterTopologyInfo clusterTopologyInfo) {
        return new CWNode(clusterId, clusterNodeId, clusterTopologyInfo);
    }

    public CWNode(int id, ClusterTopologyInfo clusterTopologyInfo) {
        super(id, clusterTopologyInfo);
    }

    public CWNode(int clusterId, int clusterNodeId, ClusterTopologyInfo clusterTopologyInfo) {
        super(clusterId, clusterNodeId, clusterTopologyInfo);
    }

    public BinTreeId getTreeClusterId() {
        return new BinTreeId(getClusterId(), getClusters());
    }

    public CWClusterNodeId getCWClusterNodeId() {
        return new CWClusterNodeId(getClusterNodeId());
    }
}
