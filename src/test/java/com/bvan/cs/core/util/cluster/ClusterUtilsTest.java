package com.bvan.cs.core.util.cluster;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ClusterUtilsTest {

    @Test
    public void testFirstClusterNodeId_correct() throws Exception {
        assertThat(ClusterUtils.firstClusterNodeId(16, 6), is(12));
    }
}