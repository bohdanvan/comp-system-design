package com.bvan.cs.gson;

import com.bvan.cs.core.AdjacencyMap;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author bvanchuhov
 */
public class GsonTest {
    private static final String jsonString = "{\n" +
            "  \"type\": \"ring\",\n" +
            "  \"cluster\": {\n" +
            "    \"verticesQuantity\": 6,\n" +
            "    \"adjacencyMap\": {\n" +
            "      \"0\": [1, 2, 3],\n" +
            "      \"1\": [0, 2, 4],\n" +
            "      \"2\": [0, 1, 5],\n" +
            "      \"3\": [0, 4, 5],\n" +
            "      \"4\": [1, 3, 5],\n" +
            "      \"5\": [2, 3, 4]\n" +
            "    }\n" +
            "  },\n" +
            "  \"clusterAdjacencyMap\": {\n" +
            "    \"2\": [0],\n" +
            "    \"1\": [1],\n" +
            "    \"4\": [4],\n" +
            "    \"5\": [3]\n" +
            "  }\n" +
            "}\n";

    private JsonObject root;

    @Before
    public void setUp() throws Exception {
        root = new JsonParser().parse(jsonString).getAsJsonObject();
    }

    @Test
    public void testGetType() throws Exception {
        String type = root.get("type").getAsString();

        assertThat(Objects.equals(type, "ring"), is(true));
    }

    @Test
    public void testReadHierarchy() throws Exception {
        String json = "{x : 1, y : 2}";

        B b = new Gson().fromJson(json, B.class);

        assertThat(b.x, is(1));
        assertThat(b.y, is(2));
    }

    @Test
    @Ignore
    public void testReadAdjacencyMap() throws Exception {
        String str = "{\n" +
                "        \"2\": [0],\n" +
                "        \"1\": [1],\n" +
                "        \"4\": [4],\n" +
                "        \"5\": [3]\n" +
                "    }";

        AdjacencyMap adjacencyMap = new Gson().fromJson(str, AdjacencyMap.class);

        System.out.println(adjacencyMap);
    }

    private static class A {
        int x;
    }

    private static class B extends A {
        int y;
    }
}
