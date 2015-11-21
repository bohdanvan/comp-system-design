package com.bvan.cs.core.adjaster;

import com.bvan.common.Tuple;

import java.util.List;

/**
 * @author bvanchuhov
 */
public class RingAdjaster implements Adjaster {
    private static LinearAdjaster linearAdjaster = new LinearAdjaster();

    @Override
    public List<Tuple<Integer>> adjustPairs(List<Integer> nodes) {
        List<Tuple<Integer>> adjustedPairs = linearAdjaster.adjustPairs(nodes);

        if (nodes.size() >= 2) {
            int left = nodes.get(nodes.size() - 1);
            int right = nodes.get(0);

            adjustedPairs.add(Tuple.of(left, right));
        }

        return adjustedPairs;
    }

    @Override
    public List<Tuple<Integer>> adjustPairs(int from, int to) {
        List<Tuple<Integer>> adjustedPairs = linearAdjaster.adjustPairs(from, to);

        if (from < to - 1) {
            adjustedPairs.add(Tuple.of(to - 1, from));
        }

        return adjustedPairs;
    }
}
