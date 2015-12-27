package com.bvan.cs.core.topology;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.topology.grid.GridTopology;
import com.bvan.cs.core.topology.linear.LinearTopology;
import com.bvan.cs.core.topology.tree.TreeLevelLinearTopology;
import com.bvan.cs.core.topology.tree.TreeLevelPairTopology;

import static com.bvan.cs.core.connector.ConnectorFactory.*;

/**
 * @author bvanchuhov
 */
public final class TopologyFactory {
    private TopologyFactory() {}

    public static Cluster simpleCluster() {
        return new Cluster(1, new AdjacencyMap());
    }

    public static AdjacencyMap simpleAdjacencyMap() {
        AdjacencyMap adjacencyMap = new AdjacencyMap();
        adjacencyMap.addEdge(0, 0);
        return adjacencyMap;
    }

    public static LinearTopology linearTopology(Topology cluster, AdjacencyMap clusterAdjacencyMap) {
        return new LinearTopology(cluster, clusterAdjacencyMap, linearConnector());
    }

    public static LinearTopology linearTopology() {
        return new LinearTopology(linearConnector());
    }

    public static LinearTopology ringTopology(Topology cluster, AdjacencyMap clusterAdjacencyMap) {
        return new LinearTopology(cluster, clusterAdjacencyMap, ringConnector());
    }

    public static LinearTopology ringTopology() {
        return new LinearTopology(ringConnector());
    }

    public static TreeLevelLinearTopology treeLevelLinearTopology() {
        return new TreeLevelLinearTopology(linearConnector());
    }

    public static TreeLevelLinearTopology treeLevelRingTopology() {
        return new TreeLevelLinearTopology(ringConnector());
    }

    public static TreeLevelLinearTopology treeLevelHypercubeTopology() {
        return new TreeLevelLinearTopology(hypercubeConnector());
    }

    public static TreeLevelPairTopology treeLevelPairLinearTopology() {
        return new TreeLevelPairTopology(linearConnector());
    }

    public static TreeLevelPairTopology treeLevelPairRingTopology() {
        return new TreeLevelPairTopology(ringConnector());
    }

    public static GridTopology gridTopology() {
        return new GridTopology(linearConnector(), linearConnector());
    }

    public static GridTopology meshTopology() {
        return new GridTopology(ringConnector(), ringConnector());
    }
}
