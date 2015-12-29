package com.bvan.cs.cw.router;

import com.bvan.common.RangeUtils;
import com.bvan.cs.cw.route.Path;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class CWRouterTest {

    private Router<Integer> router;

    @Before
    public void setUp() throws Exception {
        List<Integer> nodeIds = RangeUtils.range(100, 142);

        router = new CWRouter<>(nodeIds);
    }

    @Test
    public void testOneToOne_sameCluster_direct_noCorruptions() throws Exception {
        assertThat(router.oneToOne(106, 108), contains(
                Path.of(106),
                Path.of(108)
        ));
    }

    @Test
    public void testOneToOne_sameCluster_between_noCorruptions() throws Exception {
        assertThat(router.oneToOne(106, 110), contains(
                Path.of(106),
                Path.of(109),
                Path.of(110)
        ));
    }

    @Test
    public void testOneToOne_sameCluster_corruptions() throws Exception {
        addCorruptionsToRouter(107, 109);

        assertThat(router.oneToOne(106, 110), contains(
                Path.of(106),
                Path.of(108),
                Path.of(111),
                Path.of(110)
        ));
    }

    @Test
    public void testOneToOne_sameLevel_noCorruptions() throws Exception {
        assertThat(router.oneToOne(121, 131), contains(
                Path.of(121),
                Path.of(133),
                Path.of(130),
                Path.of(131)
        ));
    }

    @Test
    public void testOneToOne_toHigherLevel_fromLeftChild_direct_from_noCorruptions() throws Exception {
        assertThat(router.oneToOne(106, 102), contains(
                Path.of(106),
                Path.of(102)
        ));
    }

    @Test
    public void testOneToOne_toHigherLevel_fromRightChild_direct_from_noCorruptions() throws Exception {
        assertThat(router.oneToOne(115, 104), contains(
                Path.of(115),
                Path.of(104)
        ));
    }

    @Test
    public void testOneToOne_toHigherLevel_fromLeftChild_between_from_noCorruptions() throws Exception {
        assertThat(router.oneToOne(111, 102), contains(
                Path.of(111),
                Path.of(109),
                Path.of(105),
                Path.of(102)
        ));
    }

    @Test
    public void testOneToOne_toHigherLevel_fromRightChild_between_from_noCorruptions() throws Exception {
        assertThat(router.oneToOne(117, 104), contains(
                Path.of(117),
                Path.of(115),
                Path.of(104)
        ));
    }

    @Test
    public void testOneToOne_toDownLevel_leftChild_noCorruptions() throws Exception {
        assertThat(router.oneToOne(111, 121), contains(
                Path.of(111),
                Path.of(121)
        ));
    }

    @Test
    public void testOneToOne_toDownLevel_rightChild_noCorruptions() throws Exception {
        assertThat(router.oneToOne(110, 127), contains(
                Path.of(110),
                Path.of(127)
        ));
    }

    private void addCorruptionsToRouter(Integer... nodes) {
        HashSet<Integer> corruptedNodes = new HashSet<>(Arrays.asList(nodes));
        router.setCorruptedNodes(corruptedNodes);
    }
}