package com.bvan.cs.jsonio.util;

import com.bvan.cs.core.AdjacencyMap;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author bvanchuhov
 */
public final class TopologyJsonUtils {
    public static final String JSON_PARAM_TYPE = "type";

    private TopologyJsonUtils() {
    }

    public static AdjacencyMap toAdjacencyMap(Map<Integer, Set<Integer>> map) {
        return new AdjacencyMap(map);
    }

    public static List<AdjacencyMap> toAdjacencyMapList(List<Map<Integer, Set<Integer>>> mapList) {
        List<AdjacencyMap> adjacencyMapList = new ArrayList<>();

        for (Map<Integer, Set<Integer>> map : mapList) {
            adjacencyMapList.add(toAdjacencyMap(map));
        }

        return adjacencyMapList;
    }

    public static String readType(String json) {
        JsonObject jsonRoot = new JsonParser().parse(json).getAsJsonObject();
        return jsonRoot.get(JSON_PARAM_TYPE).getAsString();
    }
}
