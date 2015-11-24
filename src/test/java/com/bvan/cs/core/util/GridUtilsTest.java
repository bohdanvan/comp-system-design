package com.bvan.cs.core.util;

import org.hamcrest.Matchers;
import org.junit.Test;

import static com.bvan.cs.core.util.GridUtils.colNodes;
import static com.bvan.cs.core.util.GridUtils.rangeNodes;
import static com.bvan.cs.core.util.GridUtils.rowNodes;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class GridUtilsTest {
    @Test
    public void testRowNodes_row_1_size_3() throws Exception {
        assertThat(rowNodes(1, 3), containsInAnyOrder(3, 4, 5));
    }

    @Test
    public void testRowNodes_col_1_size_3() throws Exception {
        assertThat(colNodes(1, 3), containsInAnyOrder(1, 4, 7));
    }

    @Test
    public void testRangeNodes_from_10_to_20_step_3() throws Exception {
        assertThat(rangeNodes(10, 20, 3), containsInAnyOrder(10, 13, 16, 19));
    }
}