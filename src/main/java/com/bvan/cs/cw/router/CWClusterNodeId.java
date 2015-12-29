package com.bvan.cs.cw.router;

import java.util.ArrayList;
import java.util.List;

import static com.bvan.requirements.Requirements.requiredCorrectIndex;
import static com.bvan.requirements.Requirements.requiredNotNullArg;

public final class CWClusterNodeId {

    private static final int NODES = 6;
    private static final int LEVELS = 2;
    private static final int LEVEL_NODES = 3;

    private final int level;
    private final int levelNodeId;

    public CWClusterNodeId(int nodeId) {
        requiredCorrectIndex(nodeId, NODES, "nodeId");

        this.level = nodeId / LEVEL_NODES;
        this.levelNodeId = nodeId % LEVEL_NODES;
    }

    public CWClusterNodeId(int level, int levelNodeId) {
        this.level = requiredCorrectIndex(level, LEVELS, "level");
        this.levelNodeId = requiredCorrectIndex(levelNodeId, LEVEL_NODES, "levelNodeId");
    }

    public boolean isConnectedWithParent() {
        return getLevelNodeId() == 0;
    }

    public boolean isConnectedWithChilds() {
        return getLevelNodeId() != 0;
    }

    public CWClusterNodeId move(MoveType moveType) {
        switch (moveType) {
            default:
            case CHANGE_LEVEL:
                return new CWClusterNodeId((this.level + 1) % LEVELS, levelNodeId);
            case LEVEL_MOVE_FORWARD:
                return new CWClusterNodeId(level, (levelNodeId + 1) % LEVEL_NODES);
            case LEVEL_MOVE_BACKWARD:
                return new CWClusterNodeId(level, (levelNodeId + LEVEL_NODES - 1) % LEVEL_NODES);
        }
    }

    public int toId() {
        return level * LEVEL_NODES + levelNodeId;
    }

    public List<MoveType> nextMoves(CWClusterNodeId other) {
        requiredNotNullArg(other, "CWClusterNodeInternalId");

        List<MoveType> differences = new ArrayList<>();
        if (this.level != other.level) {
            differences.add(MoveType.CHANGE_LEVEL);
        }

        MoveType levelFirstMove = levelFirstMove(other);
        if (levelFirstMove == null) {
            levelFirstMove = MoveType.LEVEL_MOVE_FORWARD;
        }

        differences.add(levelFirstMove);
        differences.add(levelFirstMove.invert());

        return differences;
    }

    private MoveType levelFirstMove(CWClusterNodeId other) {
        if (this.getLevelNodeId() - other.getLevelNodeId() == 2) return MoveType.LEVEL_MOVE_FORWARD;
        if (other.getLevelNodeId() - this.getLevelNodeId() == 1) return MoveType.LEVEL_MOVE_FORWARD;
        if (this.getLevelNodeId() - other.getLevelNodeId() == 1) return MoveType.LEVEL_MOVE_BACKWARD;
        if (other.getLevelNodeId() - this.getLevel() == 2) return MoveType.LEVEL_MOVE_BACKWARD;

        return null;
    }

    public int distance(CWClusterNodeId other) {
        requiredNotNullArg(other, "CWClusterNodeInternalId");

        int distance = 0;
        if (this.level != other.level) {
            distance++;
        }
        if (this.levelNodeId != other.levelNodeId) {
            distance++;
        }

        return distance;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelNodeId() {
        return levelNodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CWClusterNodeId)) return false;

        CWClusterNodeId that = (CWClusterNodeId) o;

        if (level != that.level) return false;
        return levelNodeId == that.levelNodeId;
    }

    @Override
    public int hashCode() {
        int result = level;
        result = 31 * result + levelNodeId;
        return result;
    }

    public enum MoveType {
        CHANGE_LEVEL,
        LEVEL_MOVE_FORWARD,
        LEVEL_MOVE_BACKWARD;

        public MoveType invert() {
            if (this == LEVEL_MOVE_FORWARD) return LEVEL_MOVE_BACKWARD;
            if (this == LEVEL_MOVE_BACKWARD) return LEVEL_MOVE_FORWARD;
            return this;
        }
    }
}
