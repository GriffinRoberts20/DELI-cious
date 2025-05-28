package com.pluralsight.models.signature;

import com.pluralsight.models.Cheese;
import com.pluralsight.models.Meat;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.Topping;

public class PhillyCheeseSteak extends Sandwich {
    public PhillyCheeseSteak(boolean stacked) {
        super(true, "White", 8);
        this.addTopping(new Meat("Steak",stacked));
        this.addTopping(new Cheese("American",stacked));
        this.addTopping(new Topping("Peppers",false));
    }
}
