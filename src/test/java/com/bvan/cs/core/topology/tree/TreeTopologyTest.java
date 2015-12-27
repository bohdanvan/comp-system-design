package com.bvan.cs.core.topology.tree;

import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.TopologyFactory;
import com.bvan.cs.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author bvanchuhov
 */
public class TreeTopologyTest {

    private ClusterBasedTopology topology;

    @Before
    public void setUp() throws Exception {
        topology = create();
    }

    private static ClusterBasedTopology create() {
        TreeTopology topology = new TreeTopology();
        topology.setCluster(TopologyFactory.simpleCluster());
        topology.setChildrenAdjacencyMaps(Arrays.asList(
                TopologyFactory.simpleAdjacencyMap(),
                TopologyFactory.simpleAdjacencyMap()
        ));

        return topology;
    }

    @Test
    public void testTopology_level0() throws Exception {
        topology.setLevel(0);

        Graph graph = topology.getGraph();

        assertThat(topology.getNodes(), is(1));

        assertThat(graph.getNodes(), is(1));
    }

    @Test
    public void testTopology_level1() throws Exception {
        topology.setLevel(1);

        Graph graph = topology.getGraph();

        assertThat(topology.getNodes(), is(3));
        System.out.println(graph);

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0));
    }

    @Test
    public void testTopology_level2() throws Exception {
        topology.setLevel(2);

        Graph graph = topology.getGraph();

        assertThat(topology.getNodes(), is(7));

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 3, 4));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0, 5, 6));
        assertThat(graph.getAdjacents(3), containsInAnyOrder(1));
        assertThat(graph.getAdjacents(4), containsInAnyOrder(1));
        assertThat(graph.getAdjacents(5), containsInAnyOrder(2));
        assertThat(graph.getAdjacents(6), containsInAnyOrder(2));
    }
}
