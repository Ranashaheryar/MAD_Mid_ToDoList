package com.rsk.mad_mid_todolist;

import java.util.LinkedList;

public class TaskList {

    LinkedList<Item> items;
    String priority,date,time,name;
    boolean repeat;


    public TaskList() {

        items=new LinkedList<Item>();
    }

    public TaskList(LinkedList<Item> items, String priority, String date, String time, String name, boolean repeat) {
        this.items = items;
        this.priority = priority;
        this.date = date;
        this.time = time;
        this.name = name;
        this.repeat = repeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
