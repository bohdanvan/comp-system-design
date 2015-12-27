package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;
import com.bvan.cs.core.util.tree.TreeUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeAllChildsConnector implements SimpleConnector {
    private int degree;
    private int nodesQuantity;

    public TreeAllChildsConnector(int degree, int nodesQuantity) {
        this.degree = degree;
        this.nodesQuantity = nodesQuantity;
    }

    @Override
    public List<Tuple<Integer>> adjustPairs() {
        List<Tuple<Integer>> adjustedPairs = new LinkedList<>();

        for (int left = 0; left < nodesQuantity; left++) {
            List<Integer> childs = TreeUtils.childIds(left, degree, nodesQuantity);

            if (childs != null) {
                for (Integer right : childs) {
                    adjustedPairs.add(Tuple.of(left, right));
                }
            }
        }

        return adjustedPairs;
    }
}
