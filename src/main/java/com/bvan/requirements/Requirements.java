package com.bvan.requirements;

import java.util.Collection;

/**
 * @author bvanchuhov
 */
public final class Requirements {
    private Requirements() {
    }

    public static int requiredPositiveArg(int x, String message) {
        if (x <= 0) {
            throw new IllegalArgumentException(message);
        }
        return x;
    }

    public static int requiredPositiveArg(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("required positive arg");
        }
        return x;
    }

    public static int requiredNotNegativeArg(int x, String message) {
        if (x < 0) {
            throw new IllegalArgumentException(message);
        }
        return x;
    }

    public static int requiredNotNegativeArg(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("required not negative arg");
        }
        return x;
    }

    public static <T> T requiredNotNullArg(T obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }

    public static <T> T requiredNotNullArg(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("required not null value");
        }
        return obj;
    }

    public static <C extends Collection<?>> C requiredNotEmptyArg(C collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("required not empty collection");
        }
        return collection;
    }

    public static <C extends Collection<?>> C requiredNotEmptyState(C collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalStateException("required not empty collection");
        }
        return collection;
    }

    public static <T extends Comparable<T>> void requiredCorrectRange(T from, T to) {
        if (from.compareTo(to) >= 0) {
            throw new IllegalArgumentException("required correct range, but actually " + from + " >= " + to);
        }
    }

    public static int requiredCorrectIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index should be in the range [0, " + (size - 1) + "], but actually " + index);
        }
        return index;
    }
}
