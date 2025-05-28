package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Food{
    private int size;
    private String bread;
    private ArrayList<Topping> toppings;
    private boolean toasted;

    public Sandwich(boolean toasted, String bread, int size) {
        this.toasted = toasted;
        this.bread = bread;
        this.size = size;
        toppings=new ArrayList<>();
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void addTopping(Topping topping){
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping){
        this.toppings.remove(topping);
    }

    public List<Topping> getToppings(){
        return this.toppings.stream().toList();
    }

    @Override
    public double getPrice() {
        double price=0;
        price+=this.toppings.stream()
                .map(topping -> topping.getPrice(this.size))
                .reduce(0., Double::sum);
        price+=switch (this.size){
            case 4 -> 5.5;
            case 8 -> 7;
            case 12 -> 8.5;
            default -> 0;
        };
        return price;
    }

    @Override
    public String toString(){
        StringBuilder sandwich=new StringBuilder();
        double sizePrice;
        String sizeString = switch (this.size) {
            case 4 -> {
                sizePrice = 5.5;
                yield "Small 4\"";
            }
            case 8 -> {
                sizePrice = 7;
                yield "Medium 8\"";
            }
            case 12 -> {
                sizePrice = 8.5;
                yield "Large 12\"";
            }
            default -> {
                sizePrice = 0;
                yield "";
            }
        };
        sandwich.append(sizeString).append(" on ");
        if(isToasted()) sandwich.append("toasted ");
        sandwich.append(this.bread).append(" bread ").append(String.format("%.2f",sizePrice));
        List<Topping> t=getToppings();
        for(Topping topping:t){
            sandwich.append("\n  +");
            if(topping.hasExtra()) sandwich.append("extra ");
            sandwich.append(topping.getName()).append(" ").append(String.format("%.2f",topping.getPrice(this.size)));
        }
        sandwich.append("\n  Sandwich Total ").append(String.format("%.2f",this.getPrice()));
        return sandwich.toString();
    }
}
