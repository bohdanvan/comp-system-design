package com.bvan.cs.core.connector;

import com.bvan.common.Tuple;

import java.util.List;

/**
 * @author bvanchuhov
 */
public interface SimpleConnector {
    List<Tuple<Integer>> adjustPairs();
}
