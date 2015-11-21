package com.bvan.cs.core.adjaster;

import com.bvan.common.Tuple;
import com.bvan.requirements.Requirements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class LinearAdjaster implements Adjaster {
    @Override
    public List<Tuple<Integer>> adjustPairs(List<Integer> nodes) {
        Requirements.requiredNotEmptyArg(nodes);

        List<Tuple<Integer>> adjustedPairs = new ArrayList<>();

        Iterator<Integer> iterator = nodes.iterator();
        int left = iterator.next();
        int right;
        while (iterator.hasNext()) {
            right = iterator.next();

            adjustedPairs.add(Tuple.of(left, right));

            left = right;
        }

        return adjustedPairs;
    }

    @Override
    public List<Tuple<Integer>> adjustPairs(int from, int to) {
        Requirements.requiredCorrectRange(from, to);

        List<Tuple<Integer>> adjustedPairs = new ArrayList<>();

        for (int right = from + 1; right < to; right++) {
            adjustedPairs.add(Tuple.of(right - 1, right));
        }

        return adjustedPairs;
    }
}
