package com.bvan.common;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class RangeUtilsTest {

    @Test
    public void testRangeWithStep_correct() throws Exception {
        assertThat(RangeUtils.rangeWithStep(10, 4, 3), contains(10, 14, 18));
    }
}