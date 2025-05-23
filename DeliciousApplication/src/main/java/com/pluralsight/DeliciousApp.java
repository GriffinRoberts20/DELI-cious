package com.pluralsight;

import com.pluralsight.models.*;
import static com.pluralsight.util.ReceiptWriter.writeReceipt;

public class DeliciousApp {
    public static void main(String[] args) {
        Order order=new Order();
        Sandwich sandwich=new Sandwich(true,"White",8);
        sandwich.addTopping(new Meat("Steak",true));
        sandwich.addTopping(new Meat("Bacon",false));
        sandwich.addTopping(new Cheese("American",true));
        sandwich.addTopping(new Cheese("Cheddar",false));
        sandwich.addTopping(new Topping("Lettuce",false));
        sandwich.addTopping(new Topping("Onions",false));
        sandwich.addTopping(new Topping("Pickles",false));
        sandwich.addTopping(new Topping("Mayo",false));
        order.addFood(sandwich);
        order.addFood(new Drink("Coca Cola",2));
        order.addFood(new Chips("Sour Cream"));
        System.out.println(order.getReciept());
        writeReceipt(order.getReciept());
    }
}
