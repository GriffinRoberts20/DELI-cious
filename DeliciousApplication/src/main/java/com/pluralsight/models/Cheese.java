package com.pluralsight.models;

public class Cheese extends Topping{
    public Cheese(String name, boolean extra) {
        super(name, extra);
    }

    @Override
    public double getPrice(int size){
        return switch (size) {
            case 4 -> (this.hasExtra()) ? 1.05 : .75;
            case 8 -> (this.hasExtra()) ? 2.1 : 1.5;
            case 12 -> (this.hasExtra()) ? 3.15 : 2.25;
            default -> 0;
        };
    }
}
