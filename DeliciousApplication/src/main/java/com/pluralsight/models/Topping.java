package com.pluralsight.models;

public class Topping {
    private String name;
    private boolean extra;

    public Topping(String name, boolean extra) {
        this.name = name;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public double getPrice(int size){
        return 0;
    }
}
