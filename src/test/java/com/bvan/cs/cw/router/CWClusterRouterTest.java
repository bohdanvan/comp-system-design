package com.bvan.cs.cw.router;

import com.bvan.common.RangeUtils;
import com.bvan.cs.cw.route.Path;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CWClusterRouterTest {

    private Router<Integer> router;

    @Before
    public void setUp() throws Exception {
        List<Integer> nodeIds = RangeUtils.range(10, 16);

        router = new CWClusterRouter<>(nodeIds);
    }

    @Test
    public void testOneToOne_from_10_to_13_noCorruptions() throws Exception {
        assertThat(router.oneToOne(10, 13), contains(
                Path.of(10),
                Path.of(13)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_12_noCorruptions() throws Exception {
        assertThat(router.oneToOne(10, 12), contains(
                Path.of(10),
                Path.of(12)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_14_noCorruptions() throws Exception {
        assertThat(router.oneToOne(10, 14), contains(
                Path.of(10),
                Path.of(13),
                Path.of(14)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_14_corruption_13() throws Exception {
        addCorruptionsToRouter(13);

        assertThat(router.oneToOne(10, 14), contains(
                Path.of(10),
                Path.of(11),
                Path.of(14)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_14_corruptions_11_13() throws Exception {
        addCorruptionsToRouter(11, 13);

        assertThat(router.oneToOne(10, 14), contains(
                Path.of(10),
                Path.of(12),
                Path.of(15),
                Path.of(14)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_14_noRoute() throws Exception {
        addCorruptionsToRouter(11, 12, 13);

        assertThat(router.oneToOne(10, 14), is(nullValue()));
    }

    private void addCorruptionsToRouter(Integer... nodes) {
        HashSet<Integer> corruptedNodes = new HashSet<>(Arrays.asList(nodes));
        router.setCorruptedNodes(corruptedNodes);
    }
}