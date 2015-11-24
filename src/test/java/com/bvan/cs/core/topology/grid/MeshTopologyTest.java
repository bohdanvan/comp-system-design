package com.bvan.cs.core.topology.grid;

import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.TopologyFactory;
import com.bvan.cs.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import static com.bvan.cs.core.topology.TopologyFactory.simpleAdjacencyMap;
import static com.bvan.cs.core.topology.TopologyFactory.simpleCluster;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MeshTopologyTest {
    private ClusterBasedTopology topology;

    @Before
    public void setUp() throws Exception {
        topology = createMeshTopology();
    }

    private ClusterBasedTopology createMeshTopology() {
        GridTopology topology = TopologyFactory.meshTopology();
        topology.setCluster(simpleCluster());
        topology.setRowAdjacencyMap(simpleAdjacencyMap());
        topology.setColAdjacencyMap(simpleAdjacencyMap());

        return topology;
    }

    @Test
    public void testClusters1() throws Exception {
        topology.setLevel(0);

        assertThat(topology.getVerticesQuantity(), is(1));
    }

    @Test
    public void testClusters2() throws Exception {
        topology.setLevel(1);

        Graph graph = topology.getGraph();

        assertThat(topology.getVerticesQuantity(), is(4));
        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 3));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0, 3));
        assertThat(graph.getAdjacents(3), containsInAnyOrder(1, 2));

    }

    @Test
    public void testClusters3() throws Exception {
        topology.setLevel(2);

        Graph graph = topology.getGraph();

        assertThat(topology.getVerticesQuantity(), is(9));
        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2, 3, 6));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 2, 4, 7));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(1, 0, 5, 8));
        assertThat(graph.getAdjacents(3), containsInAnyOrder(0, 6, 4, 5));
        assertThat(graph.getAdjacents(4), containsInAnyOrder(3, 5, 1, 7));
        assertThat(graph.getAdjacents(5), containsInAnyOrder(4, 3, 2, 8));
        assertThat(graph.getAdjacents(6), containsInAnyOrder(7, 8, 3, 0));
        assertThat(graph.getAdjacents(7), containsInAnyOrder(6, 8, 4, 1));
        assertThat(graph.getAdjacents(8), containsInAnyOrder(7, 6, 5, 2));
    }
}
