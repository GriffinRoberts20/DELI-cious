package com.pluralsight.models.signature;

import com.pluralsight.models.Cheese;
import com.pluralsight.models.Meat;
import com.pluralsight.models.Sandwich;
import com.pluralsight.models.Topping;

public class Italian extends Sandwich {
    public Italian(boolean stacked) {
        super(false,"White",8);
        this.addTopping(new Meat("Salami",stacked));
        this.addTopping(new Meat("Ham",stacked));
        this.addTopping(new Cheese("Provolone",stacked));
        this.addTopping(new Topping("Lettuce",false));
        this.addTopping(new Topping("Tomato",false));
        this.addTopping(new Topping("Onion",false));
        this.addTopping(new Topping("Peppers",false));
        this.addTopping(new Topping("Vinaigrette",false));
    }
}
