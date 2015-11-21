package com.bvan.cs.jsonio.topology.bean;

import com.bvan.cs.core.topology.Cluster;
import com.google.gson.annotations.SerializedName;

/**
 * @author bvanchuhov
 */
public class ClusterConfBean {
    @SerializedName("cluster")
    private ClusterBean clusterBean;

    public Cluster getCluster() {
        return clusterBean.toCluster();
    }
}
