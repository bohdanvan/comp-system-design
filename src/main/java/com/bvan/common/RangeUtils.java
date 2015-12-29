package com.bvan.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author bvanchuhov
 */
public final class RangeUtils {

    private RangeUtils() {
    }

    public static List<Integer> range(int from, int toExcl) {
        return IntStream.range(from, toExcl).boxed().collect(Collectors.toList());
    }

    public static List<Integer> rangeWithStep(int from, int step, int quantity) {
        List<Integer> range = new ArrayList<>();

        while (quantity-- != 0) {
            range.add(from);

            from += step;
        }

        return range;
    }
}
