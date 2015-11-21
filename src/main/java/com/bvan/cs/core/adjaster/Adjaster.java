package com.bvan.cs.core.adjaster;

import com.bvan.common.Tuple;

import java.util.List;

/**
 * @author bvanchuhov
 */
public interface Adjaster {
    List<Tuple<Integer>> adjustPairs(List<Integer> nodes);
    List<Tuple<Integer>> adjustPairs(int from, int to);
}
