package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class TreeAllChildsConnectorTest {
    private static final int DEGREE = 2;

    @Test
    public void testTree_nodes1() throws Exception {
        assertThat(new TreeAllChildsConnector(DEGREE, 1).adjustPairs(), containsInAnyOrder());
    }

    @Test
    public void testTree_nodes3() throws Exception {
        assertThat(new TreeAllChildsConnector(DEGREE, 3).adjustPairs(), containsInAnyOrder(
                Tuple.of(0, 1),
                Tuple.of(0, 2)
        ));
    }

    @Test
    public void testTree_nodes7() throws Exception {
        assertThat(new TreeAllChildsConnector(DEGREE, 7).adjustPairs(), containsInAnyOrder(
                Tuple.of(0, 1),
                Tuple.of(0, 2),
                Tuple.of(1, 3),
                Tuple.of(1, 4),
                Tuple.of(2, 5),
                Tuple.of(2, 6)
        ));
    }
}
