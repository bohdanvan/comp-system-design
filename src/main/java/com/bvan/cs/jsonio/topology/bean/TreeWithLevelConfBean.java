package com.bvan.cs.jsonio.topology.bean;

import com.bvan.cs.core.AdjacencyMap;
import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

/**
 * @author bvanchuhov
 */
public class TreeWithLevelConfBean extends TreeConfBean {
    @SerializedName("levelAdjacencyMap")
    protected Map<Integer, Set<Integer>> rawLevelAdjacencyMap;

    public AdjacencyMap getLevelAdjacencyMap() {
        return new AdjacencyMap(rawLevelAdjacencyMap);
    }
}
