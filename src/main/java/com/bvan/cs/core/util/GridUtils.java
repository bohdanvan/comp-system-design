package com.bvan.cs.core.util;

import com.bvan.requirements.Requirements;

import java.util.ArrayList;
import java.util.List;

import static com.bvan.requirements.Requirements.requiredCorrectIndex;
import static com.bvan.requirements.Requirements.requiredCorrectRange;
import static com.bvan.requirements.Requirements.requiredNotNegativeArg;

/**
 * @author bvanchuhov
 */
public final class GridUtils {
    private GridUtils() {}

    public static List<Integer> rowNodes(int row, int size) {
        requiredCorrectIndex(row, size);

        int from = row * size;
        int to = (row + 1) * size;
        int step = 1;

        return rangeNodes(from, to, step);
    }

    public static List<Integer> colNodes(int col, int size) {
        requiredCorrectIndex(col, size);

        int from = col;
        int to = size * size + col;
        int step = size;

        return rangeNodes(from, to, step);
    }

    public static List<Integer> rangeNodes(int from, int to, int step) {
        requiredCorrectRange(from, to);
        requiredNotNegativeArg(step);

        List<Integer> nodes = new ArrayList<>();
        for (int i = from; i < to; i += step) {
            nodes.add(i);
        }

        return nodes;
    }
}
