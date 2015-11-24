package com.bvan.cs.core.util;

import org.hamcrest.Matchers;
import org.junit.Test;

import static com.bvan.cs.core.util.TreeUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class TreeUtilsTest {
    private static final int DEGREE = 2;

    //----- levelFirstNode() -----

    @Test
    public void testLevelFirstNode_level0() throws Exception {
        assertThat(levelFirstNode(0, DEGREE), is(0));
    }

    @Test
    public void testLevelFirstNode_level1() throws Exception {
        assertThat(levelFirstNode(1, DEGREE), is(1));
    }

    @Test
    public void testLevelFirstNode_level2() throws Exception {
        assertThat(levelFirstNode(2, DEGREE), is(3));
    }

    @Test
    public void testLevelLastNode_level0() throws Exception {
        assertThat(levelLastNode(0, DEGREE), is(0));
    }

    //----- levelLastNode() -----

    @Test
    public void testLevelLastNode_level1() throws Exception {
        assertThat(levelLastNode(1, DEGREE), is(2));
    }

    @Test
    public void testLevelLastNode_level2() throws Exception {
        assertThat(levelLastNode(2, DEGREE), is(6));
    }

    //----- childs() -----

    @Test
    public void testChilds_node2_nodes7() throws Exception {
        assertThat(childs(2, DEGREE, 7), containsInAnyOrder(5, 6));
    }

    //----- levelNodes() -----

    @Test
    public void testLevelNodes_level1() throws Exception {
        assertThat(levelNodes(1, DEGREE), contains(1, 2));
    }

    @Test
    public void testLevelNodes_level2() throws Exception {
        assertThat(levelNodes(2, DEGREE), contains(3, 4, 5, 6));
    }

    @Test
    public void testLevelNodes_level3() throws Exception {
        assertThat(levelNodes(3, DEGREE), contains(7, 8, 9, 10, 11, 12, 13, 14));
    }
}