package com.bvan.cs.jsonio.task.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author bvanchuhov
 */
public class MainTaskConf {
    @SerializedName("topologyConfPath")
    private String topologyConfPath;

    @SerializedName("outputFile")
    private String outputFile;

    @SerializedName("outputDir")
    private String outputDir;

    @SerializedName("outputFormat")
    private String outputFormat;

    @SerializedName("maxVerticesQuantity")
    private int maxVerticesQuantity;

    @SerializedName("maxVerticesForMinDistanceOutput")
    private int maxVerticesForMinDistanceOutput = -1;

    @SerializedName("consoleOutput")
    private boolean consoleOutput = false;

    @SerializedName("fileOutput")
    private boolean fileOutput = false;

    public String getTopologyConfPath() {
        return topologyConfPath;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public int getMaxVerticesQuantity() {
        return maxVerticesQuantity;
    }

    public int getMaxVerticesForMinDistanceOutput() {
        return maxVerticesForMinDistanceOutput;
    }

    public boolean isConsoleOutput() {
        return consoleOutput;
    }

    public boolean isFileOutput() {
        return fileOutput;
    }
}
