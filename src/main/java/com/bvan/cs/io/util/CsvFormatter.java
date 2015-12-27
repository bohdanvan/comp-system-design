package com.bvan.cs.io.util;

import com.bvan.cs.core.topology.TopologyProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author bvanchuhov
 */
public final class CsvFormatter {
    private static final String SEPARATOR = ";";
    private static final List<String> DEFAULT_TOPOLOGY_PROPERTIES_HEADER = Arrays.asList("N", "S", "D", "D*", "T", "C");

    private CsvFormatter() {}

    public static String toCsv(List<TopologyProperties> propertiesList) {
        return toCsv(DEFAULT_TOPOLOGY_PROPERTIES_HEADER, propertiesList);
    }

    public static String toCsv(List<String> header, List<TopologyProperties> propertiesList) {
        StringBuilder sb = new StringBuilder();

        appendList(sb, header);
        for (TopologyProperties properties : propertiesList) {
            appendTopologyProperties(sb, properties);
        }

        return sb.toString();
    }

    private static void appendList(StringBuilder sb, List<String> header) {
        for (String h : header) {
            sb.append(h).append(SEPARATOR);
        }
        sb.append("\n");
    }

    private static void appendTopologyProperties(StringBuilder sb, TopologyProperties properties) {
        sb.append(properties.getNodes()).append(SEPARATOR)
                .append(properties.getDegree()).append(SEPARATOR)
                .append(properties.getDiameter()).append(SEPARATOR)
                .append(formatDouble(properties.getAverageDiameter())).append(SEPARATOR)
                .append(formatDouble(properties.getTopologicalTraffic())).append(SEPARATOR)
                .append(properties.getPrice()).append(SEPARATOR)
                .append("\n");
    }

    private static String formatDouble(double d) {
        return String.format("%.3f", d);
    }
}
