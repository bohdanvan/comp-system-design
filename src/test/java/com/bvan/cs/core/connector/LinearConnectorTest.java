package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class LinearConnectorTest {
    private static final Connector adjaster = new LinearConnector();

    @Test
    public void testRange_from_5_to_10() throws Exception {
        assertThat(adjaster.connectPairs(5, 9), containsInAnyOrder(
                Tuple.of(5, 6),
                Tuple.of(6, 7),
                Tuple.of(7, 8),
                Tuple.of(8, 9)
        ));
    }

    @Test
    public void testList_from_5_to_9_incl() throws Exception {
        assertThat(adjaster.connectPairs(Arrays.asList(5, 6, 7, 8, 9)), containsInAnyOrder(
                Tuple.of(5, 6),
                Tuple.of(6, 7),
                Tuple.of(7, 8),
                Tuple.of(8, 9)
        ));
    }
}
