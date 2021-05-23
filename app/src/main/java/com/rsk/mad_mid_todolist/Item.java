package com.rsk.mad_mid_todolist;

public class Item {
    String name;
    boolean done;

    public Item() {
    }

    public Item(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", done=" + done +
                '}';
    }
}
