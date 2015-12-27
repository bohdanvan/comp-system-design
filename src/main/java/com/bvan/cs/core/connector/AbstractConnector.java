package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author bvanchuhov
 */
public abstract class AbstractConnector implements Connector {

    @Override
    public List<Tuple<Integer>> connectPairs(int fromNodeId, int toNodeIdIncl) {
        List<Integer> nodeIds = IntStream.rangeClosed(fromNodeId, toNodeIdIncl)
                .boxed()
                .collect(Collectors.toList());

        return connectPairs(nodeIds);
    }
}
