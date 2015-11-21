package com.bvan.cs.core.topology;

import com.bvan.common.CopyUtils;
import com.bvan.cs.graph.Graph;
import com.bvan.cs.graph.GraphUtils;

import java.util.Arrays;

/**
 * @author bvanchuhov
 */
public abstract class TopologyImpl implements Topology {
    private int[][] minDistancesMatrix;
    private TopologyProperties topologyProperties;

    private Graph graph;
    private int[][] adjacencyMatrix;

    @Override
    public TopologyProperties getProperties() {
        if (topologyProperties == null) {
            topologyProperties = buildTopologyProperties();
        }

        return topologyProperties;
    }

    private TopologyProperties buildTopologyProperties() {
        int[][] adjacencyMatrix = getAdjacencyMatrix();
        int[][] minDistancesMatrix = getMinDistancesMatrix();

        TopologyProperties topologyProperties = new TopologyProperties();
        topologyProperties
                .setVertices(getVerticesQuantity())
                .setDegree(GraphUtils.degree(adjacencyMatrix))
                .setDiameter(GraphUtils.diameter(minDistancesMatrix))
                .setAverageDiameter(GraphUtils.averageDiameter(minDistancesMatrix));

        return topologyProperties;
    }

    @Override
    public int[][] getMinDistancesMatrix() {
        if (minDistancesMatrix == null) {
            minDistancesMatrix = getGraph().getMinDistancesMatrix();
        }
        return CopyUtils.deepCopy(minDistancesMatrix);
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        if (adjacencyMatrix == null) {
            adjacencyMatrix = getGraph().getAdjacencyMatrix();
        }
        return CopyUtils.deepCopy(adjacencyMatrix);
    }

    @Override
    public Graph getGraph() {
        if (graph == null) {
            graph = buildGraph();
        }
        return graph;
    }

    protected void setGraph(Graph graph) {
        clearCache();

        this.graph = graph;
    }

    protected void clearCache() {
        minDistancesMatrix = null;
        topologyProperties = null;
        adjacencyMatrix = null;
        graph = null;
    }

    protected abstract Graph buildGraph();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopologyImpl)) return false;

        TopologyImpl topology = (TopologyImpl) o;

        if (!Arrays.deepEquals(minDistancesMatrix, topology.minDistancesMatrix)) return false;
        if (topologyProperties != null ? !topologyProperties.equals(topology.topologyProperties) : topology.topologyProperties != null) return false;
        if (graph != null ? !graph.equals(topology.graph) : topology.graph != null) return false;
        return Arrays.deepEquals(adjacencyMatrix, topology.adjacencyMatrix);
    }

    @Override
    public int hashCode() {
        int result = minDistancesMatrix != null ? Arrays.deepHashCode(minDistancesMatrix) : 0;
        result = 31 * result + (topologyProperties != null ? topologyProperties.hashCode() : 0);
        result = 31 * result + (graph != null ? graph.hashCode() : 0);
        result = 31 * result + (adjacencyMatrix != null ? Arrays.deepHashCode(adjacencyMatrix) : 0);
        return result;
    }

    @Override
    public String toString() {
        return getGraph().toString();
    }
}
