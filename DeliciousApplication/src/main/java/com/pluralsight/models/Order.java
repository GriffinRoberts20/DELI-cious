package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private ArrayList<Food> foods;

    public Order() {
        foods=new ArrayList<>();
    }

    public void addFood(Food food){
        foods.add(food);
    }

    public List<Food> getFoods(){
        return foods.stream().toList();
    }

    public double getTotal(){
        return foods.stream().mapToDouble(Food::getPrice).sum();
    }

    public String getReceipt(){
        StringBuilder receipt =new StringBuilder();
        foods.forEach(food -> receipt.append("-").append(food).append("\n"));
        receipt.append("Total ").append(String.format("%.2f",this.getTotal()));
        return receipt.toString();
    }
}
