package com.pluralsight.models;

public class Meat extends Topping{
    public Meat(String name, boolean extra) {
        super(name, extra);
    }

    @Override
    public double getPrice(int size){
        return switch (size) {
            case 4 -> (this.hasExtra()) ? 1.5 : 1;
            case 8 -> (this.hasExtra()) ? 3 : 2;
            case 12 -> (this.hasExtra()) ? 4.5 : 3;
            default -> 0;
        };
    }
}
