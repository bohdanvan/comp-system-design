package com.bvan.cs.cw.route;

/**
 * @author bvanchuhov
 */
public final class Path<T> {

    private final T node;
    private final PathType type;

    public static <T> Path<T> of(T node) {
        return new Path<>(node);
    }

    public static <T> Path<T> of(T node, PathType type) {
        return new Path<>(node, type);
    }

    public Path(T node, PathType type) {
        this.node = node;
        this.type = type;
    }

    public Path(T node) {
        this(node, PathType.DIRECT);
    }

    public boolean isCorrect() {
        return type.isCorrect();
    }

    public T getNode() {
        return node;
    }

    public PathType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;

        Path<?> path = (Path<?>) o;

        if (node != null ? !node.equals(path.node) : path.node != null) return false;
        return type == path.type;
    }

    @Override
    public int hashCode() {
        int result = node != null ? node.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String typeString = (type == PathType.BACK) ? " (rev)" : "";
        return node + typeString;
    }
}
