package com.bvan.common;

import java.util.Arrays;

/**
 * @author bvanchuhov
 */
public final class CopyUtils {
    private CopyUtils() {}

    public static int[][] deepCopy(int[][] matrix) {
        int[][] matrixCopy = new int[matrix.length][];

        for (int i = 0; i < matrix.length; i++) {
            matrixCopy[i] = copy(matrix[i]);
        }

        return matrixCopy;
    }

    public static int[] copy(int[] array) {
        return Arrays.copyOf(array, array.length);
    }
}
