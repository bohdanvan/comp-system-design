package com.bvan.cs.core.topology.tree;

import com.bvan.common.Tuple;
import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.connector.TreeChildsConnector;
import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.Topology;
import com.bvan.cs.core.topology.TopologyFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.bvan.requirements.Requirements.requiredNotEmptyArg;

/**
 * @author bvanchuhov
 */
public class TreeTopology extends ClusterBasedTopology {
    private static int DEFAULT_LEVEL = 0;

    protected int level = DEFAULT_LEVEL;
    protected LevelIncreaseMod levelIncreaseMod = LevelIncreaseMod.ALL_CHILDS;
    protected List<AdjacencyMap> childrenAdjacencyMaps;

    public TreeTopology(Topology cluster, List<AdjacencyMap> childrenAdjacencyMaps) {
        super(cluster);
        this.childrenAdjacencyMaps = requiredNotEmptyArg(childrenAdjacencyMaps);
    }

    public TreeTopology() {
        super();
        this.childrenAdjacencyMaps = Arrays.asList(
                TopologyFactory.simpleAdjacencyMap(),
                TopologyFactory.simpleAdjacencyMap()
        );
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        LinkedList<Adjacency> clusterAdjacencies = new LinkedList<>();

        int treeDegree = getTreeDegree();
        for (int childIndex = 0; childIndex < treeDegree; childIndex++) {
            AdjacencyMap adjacencyMap = childrenAdjacencyMaps.get(childIndex);
            List<Tuple<Integer>> adjustedClusters = new TreeChildsConnector(childIndex, treeDegree, getClusters()).adjustPairs();

            clusterAdjacencies.add(new Adjacency(adjacencyMap, adjustedClusters));
        }

        return clusterAdjacencies;
    }

    public int getTreeDegree() {
        return childrenAdjacencyMaps.size();
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    protected void setLevel0(int level) {
        this.level = level;
    }

    @Override
    public int getClusters() {
        switch (levelIncreaseMod) {
            case ALL_CHILDS:
                return (int) Math.pow(getTreeDegree(), level + 1) - 1;
            case ONE_PARENT_CHILDS:
                return 1 + level * getTreeDegree();
            default:
                throw new IllegalStateException("levelIncreaseMod in incorrect state");
        }
    }

    //----- Getters and Setters -----

    public List<AdjacencyMap> getChildrenAdjacencyMaps() {
        return childrenAdjacencyMaps;
    }

    public void setChildrenAdjacencyMaps(List<AdjacencyMap> childrenAdjacencyMaps) {
        this.childrenAdjacencyMaps = requiredNotEmptyArg(childrenAdjacencyMaps);
        clearCache();
    }

    public LevelIncreaseMod getLevelIncreaseMod() {
        return levelIncreaseMod;
    }

    public void setLevelIncreaseMod(LevelIncreaseMod levelIncreaseMod) {
        this.levelIncreaseMod = levelIncreaseMod;
    }
}
