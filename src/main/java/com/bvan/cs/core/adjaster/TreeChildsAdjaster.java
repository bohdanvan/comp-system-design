package com.bvan.cs.core.adjaster;

import com.bvan.common.Tuple;
import com.bvan.cs.core.util.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeChildsAdjaster implements SimpleAdjaster {
    private int childIndex;
    private int degree;
    private int nodesQuantity;

    public TreeChildsAdjaster(int childIndex, int degree, int nodesQuantity) {
        this.childIndex = childIndex;
        this.degree = degree;
        this.nodesQuantity = nodesQuantity;
    }

    @Override
    public List<Tuple<Integer>> adjustPairs() {
        List<Tuple<Integer>> adjustedPairs = new ArrayList<>();

        for (int parent = 0; parent < nodesQuantity; parent++) {
            int child = TreeUtils.getChild(parent, childIndex, degree);

            if (child < nodesQuantity) {
                adjustedPairs.add(Tuple.of(parent, child));
            }
        }

        return adjustedPairs;
    }
}
