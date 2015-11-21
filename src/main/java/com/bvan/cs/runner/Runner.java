package com.bvan.cs.runner;

import com.bvan.cs.jsonio.task.bean.MainTaskConf;
import com.bvan.cs.jsonio.task.factory.MainTaskConfFactory;
import com.bvan.cs.jsonio.util.JsonUtils;
import com.bvan.cs.task.MainTask;

/**
 * @author bvanchuhov
 */
public class Runner {
    public static final String CONF_PATH = "conf/general/main-task-conf.json";

    public static void main(String[] args) {
        MainTaskConf conf = readMainTaskConf(CONF_PATH);
        MainTask mainTask = new MainTask(conf);
        mainTask.doTask();
    }

    private static MainTaskConf readMainTaskConf(String confPath) {
        String json = JsonUtils.readJson(confPath);
        return MainTaskConfFactory.create(json);
    }
}
