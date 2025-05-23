package com.pluralsight.models;

public class Drink implements Food{
    private int size;
    private String flavor;

    public Drink(String flavor, int size) {
        this.flavor = flavor;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return switch(size){
            case 1 -> 2;
            case 2 -> 2.5;
            case 3 -> 3;
            default -> 0;
        };
    }

    @Override
    public String toString(){
        String sizeString=switch(size){
            case 1 -> "Small";
            case 2 -> "Medium";
            case 3 -> "Large";
            default -> "";
        };
        return sizeString+" "+this.flavor+" "+String.format("%.2f",this.getPrice());
    }
}
