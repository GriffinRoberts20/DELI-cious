package com.pluralsight.models.signature;

import com.pluralsight.models.Cheese;
import com.pluralsight.models.Meat;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.Topping;

public class BLT extends Sandwich {
    public BLT(boolean stacked) {
        super(true, "White", 8);
        this.addTopping(new Meat("Bacon",stacked));
        this.addTopping(new Cheese("Cheddar",stacked));
        this.addTopping(new Topping("Tomato",stacked));
        this.addTopping(new Topping("Ranch",false));
    }
}
