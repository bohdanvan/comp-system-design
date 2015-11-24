package com.bvan.cs.core.topology.linear;

import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.graph.Graph;
import com.bvan.cs.jsonio.topology.factory.JsonTopologyFactory;
import com.bvan.cs.jsonio.util.JsonUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

public class ComplexRingTopologyTest {
    public static final String FILE_NAME = "conf/topologies/lab1-topology.json";

    private ClusterBasedTopology topology;

    @Before
    public void setUp() throws Exception {
        String json = JsonUtils.readJson(FILE_NAME);

        topology = JsonTopologyFactory.createTopology(json, ClusterBasedTopology.class);
    }

    @Test
    public void testClusters1() throws Exception {
        topology.setLevel(0);

        Graph graph = topology.getGraph();

        assertThat(topology.getVerticesQuantity(), is(6));
        assertThat(graph.getVerticesQuantity(), is(6));

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2, 3));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 2, 4));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0, 1, 5));
        assertThat(graph.getAdjacents(3), containsInAnyOrder(0, 4, 5));
        assertThat(graph.getAdjacents(4), containsInAnyOrder(1, 3, 5));
        assertThat(graph.getAdjacents(5), containsInAnyOrder(2, 3, 4));
    }

    @Test
    public void testClusters2() throws Exception {
        topology.setLevel(1);

        Graph graph = topology.getGraph();

        System.out.println(graph);

        assertThat(topology.getVerticesQuantity(), is(12));
        assertThat(graph.getVerticesQuantity(), is (12));

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2, 3, 8));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 2, 4, 7));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0, 1, 5, 6));
        assertThat(graph.getAdjacents(3), containsInAnyOrder(0, 4, 5, 11));
        assertThat(graph.getAdjacents(4), containsInAnyOrder(1, 3, 5, 10));
        assertThat(graph.getAdjacents(5), containsInAnyOrder(2, 3, 4, 9));

        assertThat(graph.getAdjacents(6), containsInAnyOrder(7, 8, 9, 2));
        assertThat(graph.getAdjacents(7), containsInAnyOrder(6, 8, 10, 1));
        assertThat(graph.getAdjacents(8), containsInAnyOrder(6, 7, 11, 0));
        assertThat(graph.getAdjacents(9), containsInAnyOrder(6, 10, 11, 5));
        assertThat(graph.getAdjacents(10), containsInAnyOrder(7, 9, 11, 4));
        assertThat(graph.getAdjacents(11), containsInAnyOrder(8, 9, 10, 3));
    }
}
