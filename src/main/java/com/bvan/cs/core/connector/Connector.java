package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;

import java.util.List;

/**
 * @author bvanchuhov
 */
public interface Connector {
    List<Tuple<Integer>> connectPairs(List<Integer> nodeIds);
    List<Tuple<Integer>> connectPairs(int fromNodeId, int toNodeIdIncl);
}
