package com.bvan.cs.task;

import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.TopologyProperties;
import com.bvan.cs.io.util.OutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class ExperimentTask {
    private ClusterBasedTopology topology;
    private int maxVerticesQuantity;
    private int maxVerticesForMinDistanceOutput = -1;

    public ExperimentTask(ClusterBasedTopology topology, int maxVerticesQuantity) {
        this.topology = topology;
        this.maxVerticesQuantity = maxVerticesQuantity;
    }

    public List<TopologyProperties> doTask() {
        List<TopologyProperties> propertiesList = new ArrayList<>();

        topology.setLevel(0);
        while (topology.getVerticesQuantity() <= maxVerticesQuantity) {
            System.out.printf("Process...   n = %7d%n", topology.getVerticesQuantity());

            if (topology.getVerticesQuantity() <= maxVerticesForMinDistanceOutput) {
                System.out.println("Min distance matrix");
                OutputUtils.printMatrix(topology.getMinDistancesMatrix());
            }

            propertiesList.add(topology.getProperties());
            topology.increaseLevel();
        }

        return propertiesList;
    }

    public ExperimentTask setMaxVerticesForMinDistanceOutput(int maxVerticesForMinDistanceOutput) {
        this.maxVerticesForMinDistanceOutput = maxVerticesForMinDistanceOutput;
        return this;
    }
}
