package com.bvan.cs.graph;

/**
 * @author bvanchuhov
 */
public final class GraphUtils {
    private GraphUtils() {
    }

    public static int degree(int[][] adjacencyMatrix) {
        int maxAdjQuantity = Integer.MIN_VALUE;

        for (int[] row : adjacencyMatrix) {
            int adjQuantity = 0;

            for (int elem : row) {
                adjQuantity += elem;
            }

            maxAdjQuantity = Math.max(adjQuantity, maxAdjQuantity);
        }

        return maxAdjQuantity;
    }

    public static int diameter(int[][] minDistanceMatrix) {
        int verticesQuantity = minDistanceMatrix.length;
        int maxDistance = Integer.MIN_VALUE;

        for (int startVertex = 0; startVertex < verticesQuantity; startVertex++) {
            for (int finishVertex = startVertex + 1; finishVertex < verticesQuantity; finishVertex++) {
                int distance = minDistanceMatrix[startVertex][finishVertex];
                maxDistance = Math.max(distance, maxDistance);
            }
        }

        return maxDistance;
    }

    public static double averageDiameter(int[][] minDistanceMatrix) {
        int verticesQuantity = minDistanceMatrix.length;
        int distancesHalfSum = 0;

        for (int startVertex = 0; startVertex < verticesQuantity; startVertex++) {
            for (int finishVertex = startVertex + 1; finishVertex < verticesQuantity; finishVertex++) {
                int distance = minDistanceMatrix[startVertex][finishVertex];
                distancesHalfSum += distance;
            }
        }

        return (double) 2 * distancesHalfSum / (verticesQuantity * (verticesQuantity - 1));
    }
}
