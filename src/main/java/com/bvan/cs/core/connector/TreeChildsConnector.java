package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;
import com.bvan.cs.core.util.tree.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class TreeChildsConnector implements SimpleConnector {
    private int childIndex;
    private int degree;
    private int nodesQuantity;

    public TreeChildsConnector(int childIndex, int degree, int nodesQuantity) {
        this.childIndex = childIndex;
        this.degree = degree;
        this.nodesQuantity = nodesQuantity;
    }

    @Override
    public List<Tuple<Integer>> adjustPairs() {
        List<Tuple<Integer>> adjustedPairs = new ArrayList<>();

        for (int parent = 0; parent < nodesQuantity; parent++) {
            int child = TreeUtils.child(parent, childIndex, degree);

            if (child < nodesQuantity) {
                adjustedPairs.add(Tuple.of(parent, child));
            }
        }

        return adjustedPairs;
    }
}
