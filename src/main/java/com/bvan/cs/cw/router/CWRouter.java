package com.bvan.cs.cw.router;

import com.bvan.common.ListJoiner;
import com.bvan.common.RangeUtils;
import com.bvan.cs.core.util.cluster.ClusterTopologyInfo;
import com.bvan.cs.core.util.cluster.ClusterUtils;
import com.bvan.cs.core.util.hypercube.HypercubeUtils;
import com.bvan.cs.core.util.tree.BinTreeChild;
import com.bvan.cs.core.util.tree.BinTreeId;
import com.bvan.cs.core.util.tree.BinTreeUtils;
import com.bvan.cs.cw.CWNode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * @author bvanchuhov
 */
public class CWRouter<T> extends AbstractRouter<T> {

    private int CLUSTER_NODES = 6;

    public CWRouter(List<T> nodes, Set<T> corruptedNodes) {
        super(nodes, corruptedNodes);
    }

    public CWRouter(List<T> nodes) {
        super(nodes);
    }

    @Override
    protected List<Integer> getNextIds(int myId, int finishId) {
        CWNode myCWNodeId = CWNode.createByGeneralId(myId, getClusterTopologyInfo());
        CWNode finishCWNodeId = CWNode.createByGeneralId(finishId, getClusterTopologyInfo());

        CWClusterRouter<T> cwClusterRouter = prepareClusterRouter(myCWNodeId);
        HypercubeRouter<T> hypercubeRouter = prepareHypercubeRouter(myCWNodeId);

        if (myCWNodeId.getClusterId() == finishCWNodeId.getClusterId()) {
            int start = myCWNodeId.getClusterNodeId();
            int finish = finishCWNodeId.getClusterNodeId();
            List<Integer> ids = idsInClusterToGeneralIds(cwClusterRouter.getNextIds(start, finish), myCWNodeId);
            return ListJoiner.join(ids, nextHypercubeIds(myCWNodeId, hypercubeRouter));
        }

        if (myCWNodeId.getTreeClusterId().getLevel() == finishCWNodeId.getTreeClusterId().getLevel()) {
            int myTreeLevelNodeId = myCWNodeId.getTreeClusterId().getLevelNodeId();
            int finishTreeLevelNodeId = finishCWNodeId.getTreeClusterId().getLevelNodeId();
            List<Integer> idsInHypercube = hypercubeRouter.getNextIds(myTreeLevelNodeId, finishTreeLevelNodeId);
            List<Integer> ids = idsInHypercubeToGeneralIds(idsInHypercube, myCWNodeId);
            return ListJoiner.join(ids, nextClusterIds(myCWNodeId, cwClusterRouter));
        }

        if (myCWNodeId.getTreeClusterId().getLevel() > finishCWNodeId.getTreeClusterId().getLevel()) {
            if (myCWNodeId.getCWClusterNodeId().isConnectedWithParent()) {
                int treePathId = getTreePathId(myCWNodeId);
                return ListJoiner.join(
                        Arrays.asList(treePathId),
                        nextClusterIds(myCWNodeId, cwClusterRouter),
                        nextHypercubeIds(myCWNodeId, hypercubeRouter)
                );
            }
            List<Integer> ids = idsInClusterToGeneralIds(cwClusterRouter.getNextHighIds(myCWNodeId.getClusterNodeId()), myCWNodeId);
            return ListJoiner.join(
                    ids,
                    nextHypercubeIds(myCWNodeId, hypercubeRouter)
            );
        }
        if (myCWNodeId.getTreeClusterId().getLevel() < finishCWNodeId.getTreeClusterId().getLevel()) {
            if (myCWNodeId.getCWClusterNodeId().isConnectedWithChilds()) {
                int treePathId = getTreePathId(myCWNodeId);
                return ListJoiner.join(
                        Arrays.asList(treePathId),
                        nextClusterIds(myCWNodeId, cwClusterRouter),
                        nextHypercubeIds(myCWNodeId, hypercubeRouter)
                );
            }
            List<Integer> ids = idsInClusterToGeneralIds(cwClusterRouter.getNextHighIds(myCWNodeId.getClusterNodeId()), myCWNodeId);
            return ListJoiner.join(
                    ids,
                    nextHypercubeIds(myCWNodeId, hypercubeRouter)
            );
        }

        return null;
    }

    private List<Integer> nextClusterIds(CWNode myCWNodeId, CWClusterRouter<T> cwClusterRouter) {
        return idsInClusterToGeneralIds(cwClusterRouter.getNextIds(myCWNodeId.getClusterNodeId()), myCWNodeId);
    }

    private List<Integer> nextHypercubeIds(CWNode myCWNodeId, HypercubeRouter<T> hypercubeRouter) {
        int idsInHypercube = myCWNodeId.getTreeClusterId().getLevelNodeId();
        return idsInHypercubeToGeneralIds(hypercubeRouter.getNextIds(idsInHypercube), myCWNodeId);
    }

    private List<Integer> idsInHypercubeToGeneralIds(List<Integer> idsInHypercube, CWNode myCWNodeId) {
        int level = myCWNodeId.getTreeClusterId().getLevel();
        int levelFirstClusterId = BinTreeUtils.levelFirstNodeId(level);
        int clusterNodeId = myCWNodeId.getClusterNodeId();
        return idsInHypercube.stream()
                .map(id -> ClusterUtils.generalId(levelFirstClusterId + id, clusterNodeId, getClusterTopologyInfo()))
                .collect(toList());
    }

    private List<Integer> idsInClusterToGeneralIds(List<Integer> idsInCluster, CWNode myCWNodeId) {
        int clusterId = myCWNodeId.getClusterId();
        return idsInCluster.stream()
                .map(id -> ClusterUtils.generalId(clusterId, id, getClusterTopologyInfo()))
                .collect(toList());
    }

    private int getTreePathId(CWNode myCWNodeId) {
        CWClusterNodeId cwClusterNodeId = myCWNodeId.getCWClusterNodeId();
        BinTreeId treeClusterId = myCWNodeId.getTreeClusterId();

        if (treeClusterId.hasParent() && cwClusterNodeId.isConnectedWithParent()) {
            int clusterId = BinTreeUtils.parent(myCWNodeId.getClusterId());

            int clusterNodeId = -1;
            if (treeClusterId.isLeftChild()) {
                clusterNodeId = (cwClusterNodeId.getLevel() == 0) ? 2 : 5;
            } else {
                clusterNodeId = (cwClusterNodeId.getLevel() == 0) ? 1 : 4;
            }

            return ClusterUtils.generalId(clusterId, clusterNodeId, getClusterTopologyInfo());
        }
        if (treeClusterId.hasChilds() && cwClusterNodeId.isConnectedWithChilds()) {
            int clusterId, clusterNodeId;
            if (cwClusterNodeId.getLevelNodeId() == 1) {
                clusterId = BinTreeUtils.child(myCWNodeId.getClusterId(), BinTreeChild.RIGHT_CHILD);
            } else {
                clusterId = BinTreeUtils.child(myCWNodeId.getClusterId(), BinTreeChild.LEFT_CHILD);
            }
            clusterNodeId = (cwClusterNodeId.getLevel() == 0) ? 0 : 3;
            return ClusterUtils.generalId(clusterId, clusterNodeId, getClusterTopologyInfo());
        }

        return -1;
    }

    private CWClusterRouter<T> prepareClusterRouter(CWNode myCWNodeId) {
        int firstId = ClusterUtils.firstClusterNodeId(myCWNodeId.getId(), CLUSTER_NODES);
        List<Integer> ids = RangeUtils.range(firstId, firstId + CLUSTER_NODES);

        return new CWClusterRouter<>(getNodesByIds(ids));
    }

    private HypercubeRouter<T> prepareHypercubeRouter(CWNode myCWNodeId) {
        BinTreeId treeClusterId = myCWNodeId.getTreeClusterId();
        int level = treeClusterId.getLevel();
        int hypercubeDegree = level;

        int firstId = ClusterUtils.generalId(BinTreeUtils.levelFirstNodeId(level), myCWNodeId.getClusterNodeId(), getClusterTopologyInfo());
        List<Integer> ids = RangeUtils.rangeWithStep(firstId, CLUSTER_NODES, HypercubeUtils.nodes(hypercubeDegree));

        return new HypercubeRouter<>(getNodesByIds(ids), hypercubeDegree);
    }

    private ClusterTopologyInfo getClusterTopologyInfo() {
        return new ClusterTopologyInfo(getClusters(), CLUSTER_NODES);
    }

    private int getClusters() {
        return nodes.size() / CLUSTER_NODES;
    }
}
