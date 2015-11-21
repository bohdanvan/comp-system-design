package com.bvan.cs.io.util;

import java.io.*;
import java.util.List;

/**
 * @author bvanchuhov
 */
public final class OutputUtils {
    private OutputUtils() {}

    public static String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int[] rows : matrix) {
            for (int elem : rows) {
                sb.append(elem).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println(matrixToString(matrix));
    }

    public static void outputIntoFile(String path, String str) {
        try (PrintStream printStream = new PrintStream(new FileOutputStream(path), false, "UTF-8")) {
            printStream.print(str);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> void printList(List<T> list) {
        for (T elem : list) {
            System.out.println(elem);
        }
    }
}
