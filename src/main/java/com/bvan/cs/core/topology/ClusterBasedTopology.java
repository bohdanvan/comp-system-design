package com.bvan.cs.core.topology;

import com.bvan.cs.core.Adjacency;
import com.bvan.cs.core.AdjacencyMap;
import com.bvan.common.Tuple;
import com.bvan.cs.graph.Graph;
import com.bvan.cs.graph.GraphFactory;
import com.bvan.requirements.Requirements;

import java.util.List;
import java.util.Set;

import static com.bvan.requirements.Requirements.requiredNotNullArg;

/**
 * @author bvanchuhov
 */
public abstract class ClusterBasedTopology extends TopologyImpl {
    protected Topology cluster;

    public ClusterBasedTopology(Topology cluster) {
        this.cluster = requiredNotNullArg(cluster);
    }

    public ClusterBasedTopology() {
        this(TopologyFactory.simpleCluster());
    }

    @Override
    public int getVerticesQuantity() {
        return getClustersQuantity() * cluster.getVerticesQuantity();
    }

    @Override
    protected Graph buildGraph() {
        Graph graph = duplicateCluster();
        graph = adjustClusters(graph);

        return graph;
    }

    private Graph duplicateCluster() {
        int clustersQuantity = getClustersQuantity();

        Graph graph = GraphFactory.create(getVerticesQuantity());

        int clusterVerticesQuantity = cluster.getVerticesQuantity();
        Graph clusterGraph = cluster.getGraph();
        for (int clusterStartVertex = 0; clusterStartVertex < clusterVerticesQuantity; clusterStartVertex++) {
            for (Integer clusterFinishVertex : clusterGraph.getAdjacents(clusterStartVertex)) {
                for (int clusterIndex = 0; clusterIndex < clustersQuantity; clusterIndex++) {
                    int startVertex = getVertexIndex(clusterIndex, clusterStartVertex);
                    int finishVertex = getVertexIndex(clusterIndex, clusterFinishVertex);

                    graph.addEdge(startVertex, finishVertex);
                }
            }
        }

        return graph;
    }

    private Graph adjustClusters(Graph graph) {
        List<Adjacency> clusterAdjacencies = getClusterAdjacencies();

        for (Adjacency clusterAdjacency : clusterAdjacencies) {
            for (Tuple<Integer> adjustedCluster : clusterAdjacency.getClustersPairs()) {
                graph = adjustClusters(graph, adjustedCluster, clusterAdjacency.getAdjacencyMap());
            }
        }

        return graph;
    }

    private Graph adjustClusters(Graph graph, Tuple<Integer> adjustedCluster, AdjacencyMap adjacencyMap) {
        int clusterVerticesQuantity = cluster.getVerticesQuantity();

        for (int clusterAVertex = 0; clusterAVertex < clusterVerticesQuantity; clusterAVertex++) {
            Set<Integer> clusterBVertices = adjacencyMap.getAdjacents(clusterAVertex);

            if (clusterBVertices != null && !clusterBVertices.isEmpty()) {
                for (Integer clusterBVertex : clusterBVertices) {
                    int startVertex = getVertexIndex(adjustedCluster.getLeft(), clusterAVertex);
                    int finishVertex = getVertexIndex(adjustedCluster.getRight(), clusterBVertex);

                    graph.addEdge(startVertex, finishVertex);
                }
            }
        }

        return graph;
    }

    private void checkClusterAdjacencyMap(AdjacencyMap clusterAdjacencyMap) {
        if (clusterAdjacencyMap == null) {
            throw new IllegalStateException();
        }
    }

    protected int getVertexIndex(int clusterIndex, int clusterVertex) {
        return clusterIndex * cluster.getVerticesQuantity() + clusterVertex;
    }

    public void setLevel(int level) {
        setLevel0(Requirements.requiredNotNegativeArg(level, "level must be >= 0"));
        clearCache();
    }

    protected abstract List<Adjacency> getClusterAdjacencies();

    public abstract int getLevel();

    protected abstract void setLevel0(int level);

    public void increaseLevel() {
        setLevel(getLevel() + 1);
    }

    public abstract int getClustersQuantity();

    //----- Getters and Setters -----

    public Topology getCluster() {
        return cluster;
    }

    public void setCluster(Topology cluster) {
        this.cluster = requiredNotNullArg(cluster);
        clearCache();
    }
}
