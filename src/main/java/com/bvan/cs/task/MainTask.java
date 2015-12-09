package com.bvan.cs.task;

import com.bvan.cs.core.topology.ClusterBasedTopology;
import com.bvan.cs.core.topology.TopologyProperties;
import com.bvan.cs.io.util.CsvFormatter;
import com.bvan.cs.io.util.OutputUtils;
import com.bvan.cs.jsonio.task.bean.MainTaskConf;
import com.bvan.cs.jsonio.topology.factory.JsonTopologyFactory;
import com.bvan.cs.jsonio.util.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author bvanchuhov
 */
public class MainTask {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dddd-HH'h'-mm'm'");

    private MainTaskConf conf;

    public MainTask(MainTaskConf conf) {
        this.conf = conf;
    }

    public void doTask() {
        ClusterBasedTopology topology = getClusterBasedTopology();

        List<TopologyProperties> topologyProperties = createExperimentTask(topology).doTask();

        outputResult(topologyProperties);
    }

    private ExperimentTask createExperimentTask(ClusterBasedTopology topology) {
        return new ExperimentTask(topology, conf.getMaxVerticesQuantity())
                .setMaxVerticesForMinDistanceOutput(conf.getMaxVerticesForMinDistanceOutput());
    }


    private void outputResult(List<TopologyProperties> topologyProperties) {
        String result = toOutputForm(topologyProperties);

        if (conf.isFileOutput()) {
            OutputUtils.outputIntoFile(getOutputPath(), result);
        }

        if (conf.isConsoleOutput()) {
            System.out.println("Results:");
            OutputUtils.printList(topologyProperties);
        }
    }

    private String getOutputPath() {
        return String.format("%s/%s-%s.%s",
                conf.getOutputDir(),
                conf.getOutputFile(),
                getFormattedDate(),
                conf.getOutputFormat());
    }

    private String getFormattedDate() {
        return DATE_FORMAT.format(new Date());
    }

    private String toOutputForm(List<TopologyProperties> topologyProperties) {
        switch (conf.getOutputFormat()) {
            case ("csv"):
                return CsvFormatter.toCsv(topologyProperties);
            default:
                throw new IllegalStateException("Unsupported output format");
        }
    }

    private ClusterBasedTopology getClusterBasedTopology() {
        String json = JsonUtils.readJson(conf.getTopologyConfPath());

        return JsonTopologyFactory.createTopology(json, ClusterBasedTopology.class);
    }
}
