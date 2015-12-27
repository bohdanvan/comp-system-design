package com.bvan.cs.core.util.hypercube;

import org.junit.Test;

import static com.bvan.cs.core.util.hypercube.HypercubeUtils.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HypercubeUtilsTest {

    //----- degree() -----

    @Test
    public void testDegree_correct_oneNode() throws Exception {
        assertThat(degree(1), is(0));
    }

    @Test
    public void testDegree_correct_positiveNodes() throws Exception {
        assertThat(degree(10), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDegree_incorrect_zeroNodes() throws Exception {
        degree(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDegree_incorrect_negativeNodes() throws Exception {
        degree(-1);
    }

    //----- nodes() ------

    @Test
    public void testNodes_correct_zeroDegree() throws Exception {
        assertThat(nodes(0), is(1));
    }

    @Test
    public void testNodes_correct_positiveDegree() throws Exception {
        assertThat(nodes(3), is(8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNodes_incorrect_negativeDegree() throws Exception {
        nodes(-1);
    }

    //----- isAllNodes() -----

    @Test
    public void testIsAllNodes_correct_true() throws Exception {
        assertThat(isAllNodes(16, 4), is(true));
    }

    @Test
    public void testIsAllNodes_correct_false() throws Exception {
        assertThat(isAllNodes(15, 4), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsAllNodes_incorrect_notPositiveNodes() throws Exception {
        isAllNodes(0, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsAllNodes_incorrect_negativeDegree() throws Exception {
        isAllNodes(15, -1);
    }

    //----- connectedNodes -----

    @Test
    public void testConnectedNodes_correct() throws Exception {
        assertThat(connectedNodes(0b1010, 4), containsInAnyOrder(0b0010, 0b1110, 0b1000, 0b1011));
    }

    @Test
    public void testConnectedNodes_correct_zeroNode() throws Exception {
        assertThat(connectedNodes(0, 3), containsInAnyOrder(0b001, 0b010, 0b100));
    }

    @Test
    public void testConnectedNodes_correct_zeroDegree() throws Exception {
        assertThat(connectedNodes(0x1010, 0), containsInAnyOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConnectedNodes_incorrect_negativeNode() throws Exception {
        connectedNodes(-1, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConnectedNodes_incorrect_negativeDegree() throws Exception {
        connectedNodes(0b1010, -1);
    }
}