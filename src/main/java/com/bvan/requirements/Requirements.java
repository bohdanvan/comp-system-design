package com.bvan.requirements;

import java.text.MessageFormat;
import java.util.Collection;

/**
 * @author bvanchuhov
 */
public final class Requirements {

    public static final String DEFAULT_ARG_NAME = "arg";

    private Requirements() {
    }

    public static int requiredPositiveArg(int arg, String argName) {
        if (arg <= 0) {
            throw new IllegalArgumentException(MessageFormat.format("required positive {0}, but actually {0} = {1}", argName, arg));
        }
        return arg;
    }

    public static int requiredPositiveArg(int arg) {
        return requiredPositiveArg(arg, DEFAULT_ARG_NAME);
    }

    public static int requiredNotNegativeArg(int arg, String argName) {
        if (arg < 0) {
            throw new IllegalArgumentException(MessageFormat.format("required not negative {0}, but actually {0} = {1}", argName, arg));
        }
        return arg;
    }

    public static int requiredNotNegativeArg(int arg) {
        return requiredNotNegativeArg(arg, DEFAULT_ARG_NAME);
    }

    public static <T> T requiredNotNullArg(T arg, String argName) {
        if (arg == null) {
            throw new IllegalArgumentException(MessageFormat.format("{0} is null", argName));
        }
        return arg;
    }

    public static <T> T requiredNotNullArg(T arg) {
        return requiredNotNullArg(arg, DEFAULT_ARG_NAME);
    }

    public static int requiredCorrectIndex(int index, int size, String argName) {
        requiredNotNegativeArg(size, "size for " + argName);

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(MessageFormat.format("{0} should be in the range [0, {1}], but actually {0} = {2}", argName, size - 1, index));
        }
        return index;
    }

    public static int requiredCorrectIndex(int index, int size) {
        return requiredCorrectIndex(index, size, "index");
    }

    public static <T extends Comparable<T>> void requiredCorrectRange(T from, T to) {
        if (from.compareTo(to) >= 0) {
            throw new IllegalArgumentException(MessageFormat.format("required correct range, but actually {0} >= {1)", from, to));
        }
    }

    public static <C extends Collection<?>> C requiredNotEmptyArg(C collection, String collectionName) {
        requiredNotNullArg(collection, collectionName);

        if (collection.isEmpty()) {
            throw new IllegalArgumentException(MessageFormat.format("{0} is empty", collectionName));
        }
        return collection;
    }

    public static <C extends Collection<?>> C requiredNotEmptyArg(C collection) {
        return requiredNotEmptyArg(collection, "collection");
    }
}
