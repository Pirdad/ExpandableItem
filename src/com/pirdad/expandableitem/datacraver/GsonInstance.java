package com.pirdad.expandableitem.datacraver;

import com.google.gson.Gson;

public class GsonInstance {

    private static GsonInstance instance;
    private Gson gson;


    private GsonInstance() {

        gson = new Gson();

    }

    public static GsonInstance getInstance() {

        if (instance == null) {
            instance = new GsonInstance();
        }

        return instance;
    }

    public Gson getParser() {

        return gson;
    }

}
