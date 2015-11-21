package com.bvan.cs.core.topology;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.adjaster.Adjaster;
import com.bvan.cs.core.adjaster.AdjasterFactory;
import com.bvan.cs.core.adjaster.LinearAdjaster;
import com.bvan.cs.core.adjaster.RingAdjaster;
import com.bvan.cs.core.topology.grid.GridTopology;
import com.bvan.cs.core.topology.linear.LinearTopology;
import com.bvan.cs.core.topology.tree.TreeLevelLinearTopology;
import com.bvan.cs.core.topology.tree.TreeLevelPairTopology;

import java.util.List;

import static com.bvan.cs.core.adjaster.AdjasterFactory.linearAdjaster;
import static com.bvan.cs.core.adjaster.AdjasterFactory.ringAdjaster;

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
        return new LinearTopology(cluster, clusterAdjacencyMap, linearAdjaster());
    }

    public static LinearTopology linearTopology() {
        return new LinearTopology(linearAdjaster());
    }

    public static LinearTopology ringTopology(Topology cluster, AdjacencyMap clusterAdjacencyMap) {
        return new LinearTopology(cluster, clusterAdjacencyMap, ringAdjaster());
    }

    public static LinearTopology ringTopology() {
        return new LinearTopology(ringAdjaster());
    }

    public static TreeLevelLinearTopology treeLevelLinearTopology(Topology cluster, List<AdjacencyMap> childrenAdjacencyMaps, AdjacencyMap levelAdjacencyMap) {
        return new TreeLevelLinearTopology(cluster, childrenAdjacencyMaps, levelAdjacencyMap, linearAdjaster());
    }

    public static TreeLevelLinearTopology treeLevelLinearTopology() {
        return new TreeLevelLinearTopology(linearAdjaster());
    }

    public static TreeLevelLinearTopology treeLevelRingTopology(Topology cluster, List<AdjacencyMap> childrenAdjacencyMaps, AdjacencyMap levelAdjacencyMap) {
        return new TreeLevelLinearTopology(cluster, childrenAdjacencyMaps, levelAdjacencyMap, ringAdjaster());
    }

    public static TreeLevelLinearTopology treeLevelRingTopology() {
        return new TreeLevelLinearTopology(ringAdjaster());
    }

    public static TreeLevelPairTopology treeLevelPairTopology(Topology cluster, List<AdjacencyMap> childrenAdjacencyMaps, AdjacencyMap levelAdjacencyMap) {
        return new TreeLevelPairTopology(cluster, childrenAdjacencyMaps, levelAdjacencyMap);
    }

    public static TreeLevelPairTopology treeLevelPairTopology() {
        return new TreeLevelPairTopology();
    }

    public static GridTopology gridTopology() {
        return new GridTopology(linearAdjaster(), linearAdjaster());
    }

    public static GridTopology meshTopology() {
        return new GridTopology(ringAdjaster(), ringAdjaster());
    }
}
