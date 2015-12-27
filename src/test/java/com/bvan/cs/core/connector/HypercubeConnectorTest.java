package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;
import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class HypercubeConnectorTest {

    private static final Connector connector = new HypercubeConnector();

    @Test
    public void testRange_correct_singleNode() throws Exception {
        assertThat(connector.connectPairs(1, 1), containsInAnyOrder());
    }

    @Test
    public void testRange_correct_from_0_to_3() throws Exception {
        assertThat(connector.connectPairs(0, 3), containsInAnyOrder(
                Tuple.of(0, 1),
                Tuple.of(0, 2),
                Tuple.of(1, 3),
                Tuple.of(2, 3)
        ));
    }

    @Test
    public void testRange_correct_from_10_to_13() throws Exception {
        assertThat(connector.connectPairs(10, 13), containsInAnyOrder(
                Tuple.of(10, 11),
                Tuple.of(10, 12),
                Tuple.of(11, 13),
                Tuple.of(12, 13)
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRange_incorrect_notAllNodes() throws Exception {
        connector.connectPairs(10, 12);
    }
}