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
public class TreeLevelHypercubeTypologyTest {

    private ClusterBasedTopology topology;

    @Before
    public void setUp() throws Exception {
        topology = create();
    }

    private static ClusterBasedTopology create() {
        TreeLevelLinearTopology topology = TopologyFactory.treeLevelHypercubeTopology();
        topology.setCluster(TopologyFactory.simpleCluster());
        topology.setLevelAdjacencyMap(TopologyFactory.simpleAdjacencyMap());
        topology.setChildrenAdjacencyMaps(Arrays.asList(
                TopologyFactory.simpleAdjacencyMap(),
                TopologyFactory.simpleAdjacencyMap()
        ));

        return topology;
    }

    @Test
    public void testTopology_level0() throws Exception {
        topology.setLevel(0);

        assertThat(topology.getNodes(), is(1));
    }

    @Test
    public void testTopology_level1() throws Exception {
        topology.setLevel(1);

        Graph graph = topology.getGraph();

        assertThat(topology.getNodes(), is(3));

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 2));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0, 1));
    }

    @Test
    public void testTopology_level2() throws Exception {
        topology.setLevel(2);

        Graph graph = topology.getGraph();

        assertThat(topology.getNodes(), is(7));

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 2, 3, 4));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0, 1, 5, 6));
        assertThat(graph.getAdjacents(3), containsInAnyOrder(1, 4, 5));
        assertThat(graph.getAdjacents(4), containsInAnyOrder(1, 3, 6));
        assertThat(graph.getAdjacents(5), containsInAnyOrder(2, 3, 6));
        assertThat(graph.getAdjacents(6), containsInAnyOrder(2, 5, 4));
    }

    @Test
    public void testTopology_level3() throws Exception {
        topology.setLevel(3);

        Graph graph = topology.getGraph();

        assertThat(topology.getNodes(), is(15));

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1, 2));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 3, 4, 2));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(0, 5, 6, 1));
        assertThat(graph.getAdjacents(3), containsInAnyOrder(1, 7, 8, 4, 5));
        assertThat(graph.getAdjacents(4), containsInAnyOrder(1, 9, 10, 3, 6));
        assertThat(graph.getAdjacents(5), containsInAnyOrder(2, 11, 12, 3, 6));
        assertThat(graph.getAdjacents(6), containsInAnyOrder(2, 13, 14, 4, 5));
        assertThat(graph.getAdjacents(7), containsInAnyOrder(3, 8, 9, 11));
        assertThat(graph.getAdjacents(8), containsInAnyOrder(3, 7, 10, 12));
        assertThat(graph.getAdjacents(9), containsInAnyOrder(4, 7, 10, 13));
        assertThat(graph.getAdjacents(10), containsInAnyOrder(4, 8, 9, 14));
        assertThat(graph.getAdjacents(11), containsInAnyOrder(5, 7, 12, 13));
        assertThat(graph.getAdjacents(12), containsInAnyOrder(5, 8, 11, 14));
        assertThat(graph.getAdjacents(13), containsInAnyOrder(6, 9, 11, 14));
        assertThat(graph.getAdjacents(14), containsInAnyOrder(6, 10, 12, 13));
    }
}
