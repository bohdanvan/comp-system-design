package com.bvan.cs.cw.router;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bvan.cs.cw.router.CWClusterNodeId.MoveType.*;

/**
 * @author bvanchuhov
 */
public final class CWClusterRouter<T> extends AbstractRouter<T> {

    public static final int NODES = 6;

    public static final Set<Integer> HIGH_NODES = new HashSet<>(Arrays.asList(0, 3));
    public static final Set<Integer> LOWER_NODES = new HashSet<>(Arrays.asList(2, 5, 1, 4));

    public CWClusterRouter(List<T> nodes, Set<T> corruptedNodes) {
        super(nodes, corruptedNodes);

        if (nodes.size() != NODES) {
            throw new IllegalArgumentException(MessageFormat.format("nodes should be {0}, but actually {1}", NODES, nodes.size()));
        }
    }

    public CWClusterRouter(List<T> nodes) {
        this(nodes, new HashSet<T>());
    }

    @Override
    protected List<Integer> getNextIds(int myId, int finishId) {
        CWClusterNodeId myCWClusterId = new CWClusterNodeId(myId);
        CWClusterNodeId finishCWClusterId = new CWClusterNodeId(finishId);

        List<CWClusterNodeId.MoveType> nextMoves = myCWClusterId.nextMoves(finishCWClusterId);
        return movesToNextIds(nextMoves, myCWClusterId);
    }

    protected List<Integer> getNextIds(int myId) {
        CWClusterNodeId myCWClusterId = new CWClusterNodeId(myId);
        List<CWClusterNodeId.MoveType> nextMoves = Arrays.asList(CHANGE_LEVEL, LEVEL_MOVE_FORWARD, LEVEL_MOVE_BACKWARD);

        return movesToNextIds(nextMoves, myCWClusterId);
    }

    protected List<Integer> getNextHighIds(int myId) {
        CWClusterNodeId myCWClusterId = new CWClusterNodeId(myId);
        List<CWClusterNodeId.MoveType> nextMoves;

        if (myCWClusterId.getLevelNodeId() == 1) {
            nextMoves = Arrays.asList(LEVEL_MOVE_BACKWARD, CHANGE_LEVEL, LEVEL_MOVE_FORWARD);
        } else {
            nextMoves = Arrays.asList(LEVEL_MOVE_FORWARD, CHANGE_LEVEL, LEVEL_MOVE_BACKWARD);
        }

        return movesToNextIds(nextMoves, myCWClusterId);
    }

    protected List<Integer> getNextDownIds(int myId) {
        return getNextIds(myId);
    }

    private List<Integer> movesToNextIds(List<CWClusterNodeId.MoveType> nextMoves, CWClusterNodeId myCWClusterId) {
        return nextMoves.stream()
                .map(moveType -> myCWClusterId.move(moveType).toId())
                .collect(Collectors.toList());
    }

}

