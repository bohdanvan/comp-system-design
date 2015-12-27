package com.bvan.cs.core.topology.grid;

import com.bvan.common.Tuple;
import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.connector.Connector;
import com.bvan.cs.core.topology.ClusterBasedTopology;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.bvan.cs.core.util.grid.GridUtils.colNodes;
import static com.bvan.cs.core.util.grid.GridUtils.rowNodes;
import static com.bvan.requirements.Requirements.requiredNotNullArg;

/**
 * @author bvanchuhov
 */
public class GridTopology extends ClusterBasedTopology {
    protected int level = 0;

    protected AdjacencyMap rowAdjacencyMap;
    protected AdjacencyMap colAdjacencyMap;

    protected Connector rowConnector;
    protected Connector colConnector;

    public GridTopology(Connector rowConnector, Connector colConnector) {
        this.rowConnector = rowConnector;
        this.colConnector = colConnector;
    }

    @Override
    protected List<Adjacency> getClusterAdjacencies() {
        List<Tuple<Integer>> rowAdjastedClusters = new LinkedList<>();
        List<Tuple<Integer>> colAdjastedClusters = new LinkedList<>();

        for (int row = 0; row <= level; row++) {
            List<Tuple<Integer>> currentRowAdjastedClusters = rowConnector.connectPairs(rowNodes(row, getSize()));
            rowAdjastedClusters.addAll(currentRowAdjastedClusters);
        }
        for (int col = 0; col <= level; col++) {
            List<Tuple<Integer>> currentColAdjastedClusters = colConnector.connectPairs(colNodes(col, getSize()));
            colAdjastedClusters.addAll(currentColAdjastedClusters);
        }

        return Arrays.asList(
                new Adjacency(rowAdjacencyMap, rowAdjastedClusters),
                new Adjacency(colAdjacencyMap, colAdjastedClusters)
        );
    }

    private int getSize() {
        return level + 1;
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
    public int getClustersQuantity() {
        return (int) Math.pow(level + 1, 2);
    }

    //----- Getters and Setters -----

    public AdjacencyMap getRowAdjacencyMap() {
        return rowAdjacencyMap;
    }

    public void setRowAdjacencyMap(AdjacencyMap rowAdjacencyMap) {
        this.rowAdjacencyMap = requiredNotNullArg(rowAdjacencyMap);
        clearCache();
    }

    public AdjacencyMap getColAdjacencyMap() {
        return colAdjacencyMap;
    }

    public void setColAdjacencyMap(AdjacencyMap colAdjacencyMap) {
        this.colAdjacencyMap = requiredNotNullArg(colAdjacencyMap);
        clearCache();
    }
}
