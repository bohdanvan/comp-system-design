package com.bvan.cs.cw.router;

import com.bvan.common.RangeUtils;
import com.bvan.cs.core.util.hypercube.HypercubeUtils;
import com.bvan.cs.cw.route.Path;
import com.bvan.cs.cw.route.Route;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.bvan.cs.cw.route.PathType.BACK;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class HypercubeRouterTest {

    private Router<Integer> router;

    @Before
    public void setUp() throws Exception {
        int degree = 3;
        int nodes = HypercubeUtils.nodes(degree);
        List<Integer> nodeIds = RangeUtils.range(10, 10 + nodes);

        router = new HypercubeRouter<>(nodeIds, degree);
    }

    @Test
    public void testOneToOne_from_10_to_17_noCorruptions() throws Exception {
        assertThat(router.oneToOne(10, 17), contains(
                Path.of(10),
                Path.of(11),
                Path.of(13),
                Path.of(17)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_17_corruptions_10_13() throws Exception {
        addCorruptionsToRouter(11, 13);

        assertThat(router.oneToOne(10, 17), contains(
                Path.of(10),
                Path.of(12),
                Path.of(16),
                Path.of(17)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_17_corruptions_13_15() throws Exception {
        addCorruptionsToRouter(13, 15);

        assertThat(router.oneToOne(10, 17), contains(
                Path.of(10),
                Path.of(11),
                Path.of(10, BACK),
                Path.of(12),
                Path.of(16),
                Path.of(17)
        ));
    }

    @Test
    public void testOneToOne_from_10_to_17_corruptions_11_12_14_noRoute() throws Exception {
        addCorruptionsToRouter(11, 12, 14);

        Route<Integer> route = router.oneToOne(10, 17);

        assertThat(route, is(nullValue()));
    }

    private void addCorruptionsToRouter(Integer... nodes) {
        HashSet<Integer> corruptedNodes = new HashSet<>(Arrays.asList(nodes));
        router.setCorruptedNodes(corruptedNodes);
    }
}