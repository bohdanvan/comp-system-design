//package com.bvan.cs.core.util;
//
//import com.bvan.cs.io.util.OutputUtils;
//import edu.princeton.cs.algorithms.EdgeWeightedDigraph;
//import edu.princeton.cs.algorithms.Graph;
//import org.junit.Before;
//import org.junit.Test;
//
//import static com.bvan.cs.graph.GraphUtils.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//public class TopologyGraphUtil {
//    private int[][] adjacentMatrix;
//
//    @Before
//    public void setUp() throws Exception {
//        adjacentMatrix = new int[][]{
//                {0, 1, 0, 1},
//                {1, 0, 1, 0},
//                {0, 1, 0, 1},
//                {1, 0, 1, 0}
//        };
//    }
//
//    @Test
//    public void testToGraph() throws Exception {
//        Graph graph = toGraph(adjacentMatrix);
//
//        System.out.println(graph.toString());
//    }
//
//    @Test
//    public void testToEdgeWeightedDigraph() throws Exception {
//        EdgeWeightedDigraph digraph = toEdgeWeightedDigraph(toGraph(adjacentMatrix));
//
//        System.out.println(digraph);
//    }
//
//    @Test
//    public void testToMatrix() throws Exception {
//        assertThat(toMatrix(toGraph(adjacentMatrix)), is(adjacentMatrix));
//    }
//
//    @Test
//    public void testMinDistancesMatrix() throws Exception {
//        int[][] distancesMatrix = minDistancesMatrix(adjacentMatrix);
//
//        OutputUtils.printMatrix(distancesMatrix);
//    }
//}