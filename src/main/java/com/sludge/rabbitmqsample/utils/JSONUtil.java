package com.sludge.rabbitmqsample.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil<T> {

    public static String toJSONString(Object t) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(t);
    }

    public static <T> T toJavaBean(String s, Class<T> t) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, t);
    }
}
