package com.rsk.mad_mid_todolist;

import android.app.Application;

import java.util.LinkedList;

public class ApplicationData extends Application {

    public static LinkedList<TaskList> tasks;

    @Override
    public void onCreate() {
        super.onCreate();

        tasks=new LinkedList<>();


    }
}
