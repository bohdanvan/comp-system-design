package com.bvan.cs.cw.router;

import com.bvan.cs.cw.route.Route;

import java.util.Set;

/**
 * @author bvanchuhov
 */
public interface Router<T> {
    Route<T> oneToOne(T startNode, T finishNode);
    void setCorruptedNodes(Set<T> corruptedNodes);
//    List<Integer> getNextIds(int myId, int finishId);
}
