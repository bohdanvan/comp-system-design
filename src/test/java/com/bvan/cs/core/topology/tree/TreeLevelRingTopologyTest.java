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
public class TreeLevelRingTopologyTest {

    private ClusterBasedTopology topology;

    @Before
    public void setUp() throws Exception {
        topology = create();
    }

    private static ClusterBasedTopology create() {
        TreeLevelLinearTopology topology = TopologyFactory.treeLevelRingTopology();
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
        assertThat(graph.getAdjacents(3), containsInAnyOrder(1, 4, 6));
        assertThat(graph.getAdjacents(4), containsInAnyOrder(1, 3, 5));
        assertThat(graph.getAdjacents(5), containsInAnyOrder(2, 4, 6));
        assertThat(graph.getAdjacents(6), containsInAnyOrder(2, 5, 3));
    }
}
