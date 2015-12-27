package com.bvan.cs.core.util.tree;

import org.junit.Test;

import static com.bvan.cs.core.util.tree.BinTreeUtils.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class BinTreeUtilsTest {

    //----- level() -----

    @Test
    public void testLevel_correct_intermediateLevelNode() throws Exception {
        assertThat(level(10), is(3));
    }

    @Test
    public void testLevel_correct_firstLevelNode() throws Exception {
        assertThat(level(7), is(3));
    }

    @Test
    public void testLevel_correct_lastLevelNode() throws Exception {
        assertThat(level(14), is(3));
    }

    //----- levelNodes ------

    @Test
    public void testLevelNodes_correct_level0() throws Exception {
        assertThat(levelNodes(0), is(1));
    }

    @Test
    public void testLevelNodes_correct_level2() throws Exception {
        assertThat(levelNodes(2), is(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLevelNodes_incorrect_negativeLevel() throws Exception {
        levelNodes(-1);
    }

    //----- levelNodeId -----

    @Test
    public void testLevelNodeId_correct_zeroNodeId() throws Exception {
        assertThat(levelNodeId(0), is(0));
    }

    @Test
    public void testLevelNodeId_correct_positiveNodeId() throws Exception {
        assertThat(levelNodeId(4), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLevelNodeId_incorrect_negativeNodeId() throws Exception {
        assertThat(levelNodeId(-1), is(0));
    }

    //----- levelFirstNode() -----

    @Test
    public void testLevelFirstNode_correct_level0() throws Exception {
        assertThat(levelFirstNodeId(0), is(0));
    }

    @Test
    public void testLevelFirstNode_correct_level2() throws Exception {
        assertThat(levelFirstNodeId(2), is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLevelFirstNode_incorrect_negativeLevel() throws Exception {
        levelLastNodeId(-1);
    }

    //----- levelLastNode() -----

    @Test
    public void testLevelLastNode_correct_level0() throws Exception {
        assertThat(levelLastNodeId(0), is(0));
    }

    @Test
    public void testLevelLastNode_correct_level2() throws Exception {
        assertThat(levelLastNodeId(2), is(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLevelLastNode_incorrect_negativeLevel() throws Exception {
        levelLastNodeId(-1);
    }

    //----- childs() -----

    @Test
    public void testChilds_correct_node2_nodes7() throws Exception {
        assertThat(childIds(2, 7), containsInAnyOrder(5, 6));
    }

    //----- levelNodes() -----

    @Test
    public void testLevelNodes_level1() throws Exception {
        assertThat(levelNodeIds(1), contains(1, 2));
    }

    @Test
    public void testLevelNodes_level2() throws Exception {
        assertThat(levelNodeIds(2), contains(3, 4, 5, 6));
    }

    @Test
    public void testLevelNodes_level3() throws Exception {
        assertThat(levelNodeIds(3), contains(7, 8, 9, 10, 11, 12, 13, 14));
    }
}