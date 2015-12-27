package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.bvan.cs.core.util.hypercube.HypercubeUtils.*;

/**
 * @author bvanchuhov
 */
public class HypercubeConnector extends AbstractConnector {

    @Override
    public List<Tuple<Integer>> connectPairs(List<Integer> nodeIds) {
        if (!isAllNodes(nodeIds.size())) {
            throw new IllegalArgumentException("not enough nodes for hypercube, nodes = " + nodeIds.size());
        }

        Set<Tuple<Integer>> connectionPairs = new HashSet<>();

        int degree = degree(nodeIds.size());
        for (int i = 0; i < nodeIds.size(); i++) {
            List<Integer> connectedIndexes = connectedNodes(i, degree);

            Integer nodeIdA = nodeIds.get(i);
            for (Integer connectedIndex : connectedIndexes) {
                Integer nodeIdB = nodeIds.get(connectedIndex);

                Tuple<Integer> connectionPair = Tuple.of(nodeIdA, nodeIdB);
                addConnectionPair(connectionPairs, connectionPair);
            }
        }

        return new ArrayList<>(connectionPairs);
    }

    private void addConnectionPair(Set<Tuple<Integer>> connectionPairs, Tuple<Integer> connectionPair) {
        if (!isContainsConnectionPair(connectionPairs, connectionPair)) {
            connectionPairs.add(connectionPair);
        }
    }

    private boolean isContainsConnectionPair(Set<Tuple<Integer>> connectionPairs, Tuple<Integer> connectionPair) {
        return connectionPairs.contains(connectionPair) || connectionPairs.contains(connectionPair.invert());
    }
}
