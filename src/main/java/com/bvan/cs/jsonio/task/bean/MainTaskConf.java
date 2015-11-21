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

    @SerializedName("consoleOutput")
    private boolean consoleOutput;

    @SerializedName("fileOutput")
    private boolean fileOutput;

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

    public boolean isConsoleOutput() {
        return consoleOutput;
    }

    public boolean isFileOutput() {
        return fileOutput;
    }
}
