package com.bvan.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public final class ListJoiner {

    private ListJoiner() {
    }

    public static <T> List<T> join(List<T>... lists) {
        List<T> result = new ArrayList<>();

        for (List<T> list : lists) {
            result.addAll(list);
        }

        return result;
    }
}
