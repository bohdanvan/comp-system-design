package com.bvan.cs.cw.router;

import com.bvan.cs.cw.route.Path;
import com.bvan.cs.cw.route.PathType;
import com.bvan.cs.cw.route.Route;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bvan.requirements.Requirements.requiredNotNegativeArg;
import static com.bvan.requirements.Requirements.requiredNotNullArg;

/**
 * @author bvanchuhov
 */
public abstract class AbstractRouter<T> implements Router<T> {

    private static final int NO_VALUE = -1;

    protected List<T> nodes;
    protected Set<T> corruptedNodes;

    public AbstractRouter(List<T> nodes, Set<T> corruptedNodes) {
        this.nodes = requiredNotNullArg(nodes, "nodes");
        this.corruptedNodes = requiredNotNullArg(corruptedNodes, "corruptedNodes");
    }

    public AbstractRouter(List<T> nodes) {
        this(nodes, new HashSet<T>());
    }

    @Override
    public Route<T> oneToOne(T startNode, T finishNode) {
        int startId = getId(startNode);
        int finishId = getId(finishNode);

        return oneToOneInternal(startId, finishId, -1);
    }

    public Route<T> oneToOneInternal(int myId, int finishId, int prevId) {
        if (isCorruptedNode(myId)) {
            return null;
        }

        Route<T> route = new Route<>();
        route.addNextNode(getNodeById(myId));

        if (myId == finishId) {
            return route;
        }

        for (Integer nextId : getNextIds(myId, finishId)) {
            if (nextId.equals(prevId)) {
                continue;
            }

            Route<T> nextRoute = oneToOneInternal(nextId, finishId, myId);
            if (nextRoute == null) {
                continue;
            }
            if (!nextRoute.isCorrectLastPath()) {
                route.addNextRoute(nextRoute);
                continue;
            }

            route.addNextRoute(nextRoute);
            return route;
        }

        if (prevId != NO_VALUE) {
            route.addNextPath(Path.of(getNodeById(prevId), PathType.BACK));
            return route;
        }
        return null;
    }

    @Override
    public void setCorruptedNodes(Set<T> corruptedNodes) {
        this.corruptedNodes = corruptedNodes;
    }

    protected int getId(T node) {
        return nodes.indexOf(node);
    }

    protected T getNodeById(int id) {
        requiredNotNegativeArg(id);
        return nodes.get(id);
    }

    protected List<T> getNodesByIds(List<Integer> ids) {
        requiredNotNullArg(ids, "ids");
        return ids.stream().map(this::getNodeById).collect(Collectors.toList());
    }

    protected boolean isCorruptedNode(int id) {
        return corruptedNodes.contains(getNodeById(id));
    }

    protected abstract List<Integer> getNextIds(int myId, int finishId);
}
