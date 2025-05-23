package com.pluralsight.models;

public class Chips implements Food {
    private String flavor;

    public Chips(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return 1.5;
    }

    @Override
    public String toString(){
        return this.flavor+" "+String.format("%.2f",this.getPrice());
    }
}
