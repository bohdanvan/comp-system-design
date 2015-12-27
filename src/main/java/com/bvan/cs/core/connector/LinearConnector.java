package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;
import com.bvan.requirements.Requirements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class LinearConnector extends AbstractConnector {

    @Override
    public List<Tuple<Integer>> connectPairs(List<Integer> nodeIds) {
        Requirements.requiredNotEmptyArg(nodeIds);

        List<Tuple<Integer>> adjustedPairs = new ArrayList<>();

        Iterator<Integer> iterator = nodeIds.iterator();
        int left = iterator.next();
        int right;
        while (iterator.hasNext()) {
            right = iterator.next();

            adjustedPairs.add(Tuple.of(left, right));

            left = right;
        }

        return adjustedPairs;
    }
}
