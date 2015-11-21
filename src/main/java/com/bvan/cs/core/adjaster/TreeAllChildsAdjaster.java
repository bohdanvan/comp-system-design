package com.bvan.cs.core.adjaster;

import com.bvan.common.Tuple;
import com.bvan.cs.core.util.TreeUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeAllChildsAdjaster implements SimpleAdjaster {
    private int degree;
    private int nodesQuantity;

    public TreeAllChildsAdjaster(int degree, int nodesQuantity) {
        this.degree = degree;
        this.nodesQuantity = nodesQuantity;
    }

    @Override
    public List<Tuple<Integer>> adjustPairs() {
        List<Tuple<Integer>> adjustedPairs = new LinkedList<>();

        for (int left = 0; left < nodesQuantity; left++) {
            List<Integer> childs = TreeUtils.childs(left, degree, nodesQuantity);

            if (childs != null) {
                for (Integer right : childs) {
                    adjustedPairs.add(Tuple.of(left, right));
                }
            }
        }

        return adjustedPairs;
    }
}
