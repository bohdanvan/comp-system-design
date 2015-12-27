package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;

import java.util.List;

/**
 * @author bvanchuhov
 */
public class RingConnector extends AbstractConnector {

    private static LinearConnector linearConnector = new LinearConnector();

    @Override
    public List<Tuple<Integer>> connectPairs(List<Integer> nodeIds) {
        List<Tuple<Integer>> adjustedPairs = linearConnector.connectPairs(nodeIds);

        if (nodeIds.size() >= 2) {
            int left = nodeIds.get(nodeIds.size() - 1);
            int right = nodeIds.get(0);

            adjustedPairs.add(Tuple.of(left, right));
        }

        return adjustedPairs;
    }
}
