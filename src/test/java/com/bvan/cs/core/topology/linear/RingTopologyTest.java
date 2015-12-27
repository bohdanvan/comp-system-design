package com.bvan.cs.core.topology.linear;

import com.bvan.cs.core.AdjacencyMap;
import com.bvan.cs.core.topology.Cluster;
import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.TopologyFactory;
import com.bvan.cs.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import static com.bvan.cs.core.topology.TopologyFactory.simpleAdjacencyMap;
import static com.bvan.cs.core.topology.TopologyFactory.simpleCluster;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author bvanchuhov
 */
public class RingTopologyTest {
    private ClusterBasedTopology topology;

    @Before
    public void setUp() throws Exception {
        Cluster cluster = simpleCluster();
        AdjacencyMap adjacencyMap = simpleAdjacencyMap();

        topology = TopologyFactory.ringTopology(cluster, adjacencyMap);
    }

    @Test
    public void testClusters1() throws Exception {
        topology.setLevel(0);

        Graph graph = topology.getGraph();

        assertThat(topology.getNodes(), is(1));
        assertThat(graph.getNodes(), is(1));
    }

    @Test
    public void testClusters2() throws Exception {
        topology.setLevel(1);

        Graph graph = topology.getGraph();

        assertThat(graph.getAdjacents(0), containsInAnyOrder(1));
    }

    @Test
    public void testClusters3() throws Exception {
        topology.setLevel(2);

        Graph graph = topology.getGraph();

        assertThat(graph.getAdjacents(0), containsInAnyOrder(2, 1));
        assertThat(graph.getAdjacents(1), containsInAnyOrder(0, 2));
        assertThat(graph.getAdjacents(2), containsInAnyOrder(1, 0));
    }
}
