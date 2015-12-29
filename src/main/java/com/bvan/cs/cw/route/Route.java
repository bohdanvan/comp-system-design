package com.bvan.cs.cw.route;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author bvanchuhov
 */
public final class Route<T> implements Iterable<Path<T>> {

    private List<Path<T>> paths;

    public Route(List<T> nodes) {
        this.paths = toPaths(nodes);
    }

    public Route() {
        this(new ArrayList<>());
    }

    private List<Path<T>> toPaths(Collection<T> nodes) {
        return nodes.stream().map(Path::of).collect(Collectors.toList());
    }

    public Route<T> addNextRoute(Route<T> route) {
        return addNextPaths(route.getPaths());
    }

    public Route<T> addNextNode(T node) {
        return addNextPath(Path.of(node));
    }

    public Route<T> addNextPath(Path<T> path) {
        paths.add(path);
        return this;
    }

    public Route<T> addNextNodes(Collection<T> newNodes) {
        return addNextPaths(toPaths(newNodes));
    }

    public Route<T> addNextPaths(Collection<Path<T>> newPaths) {
        paths.addAll(newPaths);
        return this;
    }

    public T getLastNode() {
        return getLastPath().getNode();
    }

    public boolean isCorrectLastPath() {
        return getLastPath().getType().isCorrect();
    }

    public Path<T> getLastPath() {
        if (paths.isEmpty()) {
            throw new NoSuchElementException("no paths in route");
        }

        return paths.get(paths.size() - 1);
    }

    public List<Path<T>> getPaths() {
        return paths;
    }

    public List<T> getNodes() {
        return paths.stream().map(Path::getNode).collect(Collectors.toList());
    }

    public int size() {
        return paths.size();
    }

    @Override
    public Iterator<Path<T>> iterator() {
        return paths.iterator();
    }

    @Override
    public String toString() {
        return String.join(" -> ", paths.stream().map(Path::toString).collect(Collectors.toList()));
    }
}
