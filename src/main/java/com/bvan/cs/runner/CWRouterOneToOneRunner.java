package com.bvan.cs.runner;

import com.bvan.common.RangeUtils;
import com.bvan.cs.cw.route.Route;
import com.bvan.cs.cw.router.CWRouter;
import com.bvan.cs.cw.router.Router;

import java.util.*;

/**
 * @author bvanchuhov
 */
public class CWRouterOneToOneRunner {

    private static final int CLUSTER_NODES = 6;
    private static final int CLUSTERS = 7;
    private static final int NODES = CLUSTERS * CLUSTER_NODES;

    private static final Set<Integer> CORRUPTED_NODES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
        24, 30
    )));

    private static final int START_NODE = 8;
    private static final int FINISH_NODE = 25;

    public static void main(String[] args) {
        List<Integer> nodeIds = RangeUtils.range(0, NODES);

        Router<Integer> router = new CWRouter<>(nodeIds);
        router.setCorruptedNodes(CORRUPTED_NODES);

        Route<Integer> route = router.oneToOne(START_NODE, FINISH_NODE);

        System.out.println("Route");
        System.out.println(route);
    }
}
