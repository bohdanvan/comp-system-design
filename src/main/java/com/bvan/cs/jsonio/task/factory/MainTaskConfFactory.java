package com.bvan.cs.jsonio.task.factory;

import com.bvan.cs.jsonio.task.bean.MainTaskConf;
import com.google.gson.Gson;

/**
 * @author bvanchuhov
 */
public final class MainTaskConfFactory {
    private MainTaskConfFactory() {}

    public static MainTaskConf create(String json) {
        return new Gson().fromJson(json, MainTaskConf.class);
    }
}
