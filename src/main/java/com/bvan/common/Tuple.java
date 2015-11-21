package com.bvan.common;

/**
 * @author bvanchuhov
 */
public final class Tuple<T> {
    private final T left;
    private final T right;

    public static <T> Tuple<T> of(T left, T right) {
        return new Tuple<>(left, right);
    }

    public Tuple(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;

        Tuple<?> tuple = (Tuple<?>) o;

        if (left != null ? !left.equals(tuple.left) : tuple.left != null) return false;
        return !(right != null ? !right.equals(tuple.right) : tuple.right != null);
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", left, right);
    }
}
