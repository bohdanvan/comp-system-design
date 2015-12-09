package com.bvan.cs.jsonio.topology.factory;

import com.bvan.cs.core.topology.Topology;
import com.bvan.cs.core.topology.TopologyFactory;
import com.bvan.cs.core.topology.grid.GridTopology;
import com.bvan.cs.core.topology.linear.LinearTopology;
import com.bvan.cs.core.topology.tree.*;
import com.bvan.cs.jsonio.topology.bean.*;
import com.bvan.cs.jsonio.util.TopologyJsonUtils;
import com.google.gson.Gson;

/**
 * @author bvanchuhov
 */
public final class JsonTopologyFactory {
    private JsonTopologyFactory() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends Topology> T createTopology(String json, Class<T> topologyClass) {
        switch (getType(json)) {
            case "cluster":
                return (T) new Gson().fromJson(json, ClusterConfBean.class).getCluster();
            case "linear":
                return (T) createLinearTopology(new Gson().fromJson(json, LinearConfBean.class));
            case "ring":
                return (T) createRingTopology(new Gson().fromJson(json, LinearConfBean.class));
            case "tree":
                return (T) createTreeTopology(new Gson().fromJson(json, TreeConfBean.class));
            case "tree-level-linear":
                return (T) createTreeLevelLinearTopology(new Gson().fromJson(json, TreeWithLevelConfBean.class));
            case "tree-level-ring":
                return (T) createTreeLevelRingTopology(new Gson().fromJson(json, TreeWithLevelConfBean.class));
            case "tree-level-pair-linear":case "tree-level-pair":
                return (T) creteTreeLevelPairLinearTopology(new Gson().fromJson(json, TreeWithLevelConfBean.class));
            case "tree-level-pair-ring":
                return (T) createTreeLevelPairRingTopology(new Gson().fromJson(json, TreeWithLevelConfBean.class));
            case "grid":
                return (T) createGridTopology(new Gson().fromJson(json, GridConfBean.class));
            case "mesh":
                return (T) createMeshTopology(new Gson().fromJson(json, GridConfBean.class));
            default:
                throw new IllegalArgumentException("Unsupported topology type");
        }
    }

    private static String getType(String json) {
        return TopologyJsonUtils.readType(json);
    }

    private static LinearTopology createLinearTopology(LinearConfBean confBean) {
        return TopologyFactory.linearTopology(confBean.getCluster(), confBean.getClusterAdjacencyMap());
    }

    private static LinearTopology createRingTopology(LinearConfBean confBean) {
        return TopologyFactory.ringTopology(confBean.getCluster(), confBean.getClusterAdjacencyMap());
    }


    private static TreeTopology createTreeTopology(TreeConfBean confBean) {
        TreeTopology topology = new TreeTopology();
        initTreeTopology(topology, confBean);
        return topology;
    }

    private static TreeLevelLinearTopology createTreeLevelLinearTopology(TreeWithLevelConfBean confBean) {
        TreeLevelLinearTopology topology = TopologyFactory.treeLevelLinearTopology();
        initTreeWithLevelTopology(topology, confBean);
        return topology;
    }

    private static TreeLevelLinearTopology createTreeLevelRingTopology(TreeWithLevelConfBean confBean) {
        TreeLevelLinearTopology topology = TopologyFactory.treeLevelRingTopology();
        initTreeWithLevelTopology(topology, confBean);
        return topology;
    }

    private static TreeLevelPairTopology creteTreeLevelPairLinearTopology(TreeWithLevelConfBean confBean) {
        TreeLevelPairTopology topology = TopologyFactory.treeLevelPairLinearTopology();
        initTreeWithLevelTopology(topology, confBean);
        return topology;
    }

    private static TreeTopology createTreeLevelPairRingTopology(TreeWithLevelConfBean confBean) {
        TreeLevelPairTopology topology = TopologyFactory.treeLevelPairRingTopology();
        initTreeWithLevelTopology(topology, confBean);
        return topology;
    }

    private static GridTopology createGridTopology(GridConfBean confBean) {
        GridTopology topology = TopologyFactory.gridTopology();
        initGreedTopology(topology, confBean);
        return topology;
    }

    private static GridTopology createMeshTopology(GridConfBean confBean) {
        GridTopology gridTopology = TopologyFactory.meshTopology();
        initGreedTopology(gridTopology, confBean);
        return gridTopology;
    }

    private static void initTreeTopology(TreeTopology topology, TreeConfBean confBean) {
        LevelIncreaseMod levelIncreaseMod = (confBean.getLevelIncreaseMod() != null) ?
                LevelIncreaseMod.of(confBean.getLevelIncreaseMod()) : LevelIncreaseMod.DEFAULT;
        topology.setLevelIncreaseMod(levelIncreaseMod);

        topology.setCluster(confBean.getCluster());
        topology.setChildrenAdjacencyMaps(confBean.getChildrenAdjacencyMaps());
    }

    private static void initTreeWithLevelTopology(TreeWithLevelTopology topology, TreeWithLevelConfBean confBean) {
        initTreeTopology(topology, confBean);
        topology.setLevelAdjacencyMap(confBean.getLevelAdjacencyMap());
    }

    private static void initGreedTopology(GridTopology topology, GridConfBean confBean) {
        topology.setCluster(confBean.getCluster());
        topology.setRowAdjacencyMap(confBean.getRowClusterAdjacencyMap());
        topology.setColAdjacencyMap(confBean.getColClusterAdjacencyMap());
    }
}
