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
    private int maxNodes;
    private int maxNodesForMinDistanceOutput = -1;

    public ExperimentTask(ClusterBasedTopology topology, int maxNodes) {
        this.topology = topology;
        this.maxNodes = maxNodes;
    }

    public List<TopologyProperties> doTask() {
        List<TopologyProperties> propertiesList = new ArrayList<>();

        topology.setLevel(0);
        while (topology.getNodes() <= maxNodes) {
            System.out.printf("Process...   n = %7d%n", topology.getNodes());

            if (topology.getNodes() <= maxNodesForMinDistanceOutput) {
                System.out.println("Min distance matrix");
                OutputUtils.printMatrix(topology.getMinDistancesMatrix());
            }

            propertiesList.add(topology.getProperties());
            topology.increaseLevel();
        }

        return propertiesList;
    }

    public ExperimentTask setMaxNodesForMinDistanceOutput(int maxNodesForMinDistanceOutput) {
        this.maxNodesForMinDistanceOutput = maxNodesForMinDistanceOutput;
        return this;
    }
}
