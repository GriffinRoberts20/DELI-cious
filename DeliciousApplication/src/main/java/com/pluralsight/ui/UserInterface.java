package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.models.signature.BLT;
import com.pluralsight.models.signature.ChickenBaconCheddar;
import com.pluralsight.models.signature.Italian;
import com.pluralsight.models.signature.PhillyCheeseSteak;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static com.pluralsight.util.ReceiptWriter.writeReceipt;

public class UserInterface {

    //runs interface
    public static void display() throws InterruptedException {
        homeMenu();
    }

    //home menu, start new order or exit
    public static void homeMenu() throws InterruptedException {

        boolean gettingChoice = true;
        while(gettingChoice){
            newScreen();
            System.out.println("          DELI-CIOUS");
            divider(30);
            System.out.println("   1) New Order");
            System.out.println("   0) Exit");
            switch (getChoice("Enter choice: ")) {
                case "1":
                    orderMenu();
                    break;
                case "0":
                    System.out.println("Closing application.");
                    gettingChoice=false;
                    break;
                default:
                    System.out.println("Invalid input, must be 0-1.");
            }
            if(gettingChoice) Thread.sleep(2500);
        }
    }

    //order menu, add sandwich, add drink, add chips, checkout, cancel order
    public static void orderMenu() throws InterruptedException {
        Order order=new Order();
        boolean gettingChoice=true;
        while(gettingChoice){
            newScreen();
            System.out.println("          Order Menu");
            divider(30);
            System.out.println("   1) Add Sandwich");
            System.out.println("   11) Add Signature Sandwich");
            System.out.println("   2) Add Drink");
            System.out.println("   3) Add Chips");
            System.out.println("   4) Checkout");
            System.out.println("   0) Cancel Order");
            switch (getChoice("Enter choice: ")){
                case "1": //gets sandwich order, checks if user cancelled sandwich order, if not adds sandwich to order
                    Sandwich sandwich=getSandwich();
                    if(sandwich==null) break;
                    order.addFood(sandwich);
                    break;
                case "11":
                    Sandwich signature=getSignatureSandwich();
                    if(signature==null) break;
                    order.addFood(signature);
                    break;
                case "2": //gets drink order, checks if user cancelled drink order, if not adds drink to order
                    Drink drink=getDrink();
                    if(drink==null) break;
                    order.addFood(drink);
                    break;
                case "3": //gets chip order, checks if user cancelled chip order, if not adds chips to order
                    Chips chips=getChips();
                    if(chips==null) break;
                    order.addFood(chips);
                    break;
                case "4":/*checks if order has food in it, gets user confirmation on order, returns to home if checked out,
                 if checkout not confirmed or order is empty, asks if user wants to continue ordering*/
                    boolean checkedOut=false;
                    if(!order.isEmpty()) checkedOut=checkout(order);
                    else System.out.println("Order is empty.");
                    if(!checkedOut){
                        if(getChoice("Enter Y to continue ordering: ").equalsIgnoreCase("Y")) break;
                    }
                    gettingChoice=false;
                    order=new Order();
                    break;
                case "0": //cancel order and return to home
                    System.out.println("Canceling order.");
                    gettingChoice=false;
                    order=new Order();
                    break;
                default:
                    System.out.println("Invalid input, must be 0-4.");
            }
            if(gettingChoice) Thread.sleep(2500);
        }
    }

    //gets sandwich order
    public static Sandwich getSandwich() throws InterruptedException {
        Sandwich sandwich=null;
        boolean cancel=false;
        while(true){
            //get sandwich size
            int size=getSandwichSize();
            //cancel order
            if(size==0){
                break;
            }
            //get sandwich bread
            String bread=getSandwichBread();
            //cancel order
            if(bread.equals("exit")){
                break;
            }
            //get sandwich toasted
            boolean toasted=false;
            switch (wantToasted()){
                case "true":
                    toasted=true;
                    break;
                case "false":
                    break;
                case "exit":
                    cancel=true;
            }
            //cancel order
            if(cancel) break;
            sandwich=new Sandwich(toasted,bread,size);
            //get toppings
            sandwich=addToppings(sandwich);
            //cancel order
            if(sandwich==null) cancel=true;
            //confirm choices, if wrong ask if user wants to remake
            if(!cancel) {
                if(!confirmFood(sandwich)) {
                    sandwich=null;
                    if (getChoice("Enter Y to remake sandwich: ").equalsIgnoreCase("Y")) {
                        continue;
                    }
                }
            }
            break;
        }
        System.out.println("Returning to order menu.");
        Thread.sleep(2500);
        return sandwich;
    }

    //gets signature sandwich order
    public static Sandwich getSignatureSandwich() throws InterruptedException{
        Sandwich sandwich;
        boolean cancel=false;
        while(true){
            sandwich=getBase();
            //cancel order
            if(sandwich==null) break;
            //remove toppings
            if(getChoice("Enter Y to remove toppings: ").equalsIgnoreCase("Y")){
                sandwich=editSignature(sandwich);
            }
            //cancel order
            if(sandwich==null) break;
            //add toppings
            if(getChoice("Enter Y to add toppings: ").equalsIgnoreCase("Y")){
                sandwich=addToppings(sandwich);
            }
            //cancel order
            if(sandwich==null) cancel=true;
            //confirm choices, if wrong ask if user wants to remake
            if(!cancel) {
                if(!confirmFood(sandwich)) {
                    sandwich=null;
                    if (getChoice("Enter Y to remake sandwich: ").equalsIgnoreCase("Y")) {
                        continue;
                    }
                }
            }
            break;
        }
        System.out.println("Returning to order menu.");
        Thread.sleep(2500);
        return sandwich;
    }

    //gets base signature sandwich
    public static Sandwich getBase() throws InterruptedException {
        Sandwich sandwich;
        while(true) {
            newScreen();
            System.out.println("          Signatures");
            divider(30);
            System.out.println("   1) Italian");
            System.out.println("   2) Philly Cheese Steak");
            System.out.println("   3) Chicken Bacon Cheddar");
            System.out.println("   4) BLT");
            System.out.println("   0) Cancel");
            String signatureChoice=getChoice("Choose your sandwich: ");
            if(signatureChoice.equalsIgnoreCase("0")){
                sandwich=null;
                break;
            }
            List<String> options= List.of(new String[]{"1", "2", "3", "4"});
            if(!options.contains(signatureChoice)){
                System.out.println("Invalid Signature Sandwich Choice, must choose 1-4, or 0 to cancel.");
                Thread.sleep(2500);
                continue;
            }
            else {
                boolean stacked=getChoice("Enter Y to make your sandwich Stacked: ").equalsIgnoreCase("Y");
                sandwich = switch (signatureChoice) {
                    case "1" -> new Italian(stacked);
                    case "2" -> new PhillyCheeseSteak(stacked);
                    case "3" -> new ChickenBaconCheddar(stacked);
                    case "4" -> new BLT(stacked);
                    default -> null;
                };
            }
            break;
        }
        return sandwich;
    }

    public static Sandwich editSignature(Sandwich sandwich){
        while(true){
            newScreen();
            System.out.println("           Toppings");
            divider(30);
            AtomicInteger i=new AtomicInteger(1);
            Sandwich finalSandwich = sandwich;
            sandwich.getToppings().forEach(topping -> {
                System.out.print("   "+i.getAndIncrement()+") ");
                if(topping.hasExtra()) System.out.print("extra "+topping.getName());
                else System.out.print(topping.getName());
                System.out.printf(" %.2f%n",topping.getPrice(finalSandwich.getSize()));
            });
            System.out.println("   99) Done removing toppings");
            System.out.println("   0) Cancel");
            String indexString=getChoice("Choose Topping to remove: ");
            //cancel order
            if(indexString.equals("0")) sandwich=null;
            //exit loop
            if(indexString.equals("99")||indexString.equals("0")) break;
            int indexToRemove;
            try{
                indexToRemove=Integer.parseInt(indexString)-1;
                if(indexToRemove>=sandwich.getToppings().size()||indexToRemove<0){
                    System.out.println("Invalid choice, must choose 1-"+sandwich.getToppings().size()+", 99 to finish removing toppings, or 0 to cancel order.");
                    continue;
                }
            } catch (Exception e){
                System.out.println("Invalid choice, must choose 1-"+sandwich.getToppings().size()+", 99 to finish removing toppings, or 0 to cancel order.");
                continue;
            }
            sandwich.removeTopping(sandwich.getToppings().get(indexToRemove));
        }
        return sandwich;
    }

    //gets sandwich size choice
    public static int getSandwichSize() throws InterruptedException {
        int size;
        while(true) {
            newScreen();
            System.out.println("             Size");
            divider(30);
            System.out.println("   4) Small $5.50");
            System.out.println("   8) Medium $7.00");
            System.out.println("   12) Large $8.50");
            System.out.println("   0) Cancel");
            size = switch (getChoice("Choose your size: ")) {
                case "4" -> 4;
                case "8" -> 8;
                case "12" -> 12;
                case "0" -> 0;
                default -> 1;
            };
            if(size==1){
                System.out.println("Invalid size choice: must choose  4, 8, or 12, or 0 to cancel.");
                Thread.sleep(2500);
                continue;
            }
            break;
        }
        return size;
    }

    //gets sandwich bread choice
    public static String getSandwichBread() throws InterruptedException {
        String bread;
        while (true) {
            newScreen();
            System.out.println("            Bread");
            divider(30);
            System.out.println("   1) White");
            System.out.println("   2) Wheat");
            System.out.println("   3) Rye");
            System.out.println("   4) Wrap");
            System.out.println("   0) Cancel");
            bread = switch (getChoice("Choose your bread: ")) {
                case "1" -> "White";
                case "2" -> "Wheat";
                case "3" -> "Rye";
                case "4" -> "Wrap";
                case "0" -> "exit";
                default -> "invalid";
            };
            if(bread.equals("exit")){
                break;
            }
            if(bread.equals("invalid")){
                System.out.println("Invalid bread choice: must choose 1-4, or 0 to cancel");
                Thread.sleep(2500);
                continue;
            }
            break;
        }
        return bread;
    }

    //gets toasted choice
    public static String wantToasted() throws InterruptedException {
        String toasted;

        while(true){
            newScreen();
            System.out.println("Do you want your sandwich toasted?");
            divider(30);
            System.out.println("   1) Yes");
            System.out.println("   2) No");
            System.out.println("   0) Cancel");
            switch (getChoice("Enter choice: ")){
                case "1":
                    toasted="true";
                    break;
                case "2":
                    toasted="false";
                    break;
                case "0":
                    toasted="exit";
                    break;
                default:
                    System.out.println("Invalid toasted choice, must choose 1-2, or 0 to cancel.");
                    Thread.sleep(2500);
                    continue;
            }
            break;
        }
        return toasted;
    }

    //gets toppings choices
    public static Sandwich addToppings(Sandwich sandwich) throws InterruptedException {
        Sandwich sandwichPlusMeat=addMeats(sandwich);
        if(sandwichPlusMeat==null) return null;
        Sandwich sandwichPlusCheese=addCheese(sandwichPlusMeat);
        if(sandwichPlusCheese==null) return null;
        Sandwich sandwichPlusFreebies=addFreebies(sandwichPlusCheese);
        if(sandwichPlusFreebies==null) return null;
        return addSauce(sandwichPlusFreebies);
    }

    //gets meat toppings choices
    public static Sandwich addMeats(Sandwich sandwich) throws InterruptedException {
        boolean extra;
        boolean gettingMeats=true;
        while(gettingMeats) {
            newScreen();
            String meatType;
            System.out.print("          Meats ");
            switch (sandwich.getSize()){
                case 4->System.out.println("+$1");
                case 8->System.out.println("+$2");
                case 12->System.out.println("+$3");
            }
            divider(30);
            System.out.println("   1) Steak");
            System.out.println("   2) Ham");
            System.out.println("   3) Salami");
            System.out.println("   4) Roast Beef");
            System.out.println("   5) Chicken");
            System.out.println("   6) Bacon");
            System.out.println("   99) Done with meats");
            System.out.println("   0) Cancel");
            switch (getChoice("Enter choice: ")){
                case "1":
                    meatType="Steak";
                    break;
                case "2":
                    meatType="Ham";
                    break;
                case "3":
                    meatType="Salami";
                    break;
                case "4":
                    meatType="Roast Beef";
                    break;
                case "5":
                    meatType="Chicken";
                    break;
                case "6":
                    meatType="Bacon";
                    break;
                case "99":
                    gettingMeats=false;
                    continue;
                case "0":
                    gettingMeats=false;
                    sandwich=null;
                    continue;
                default:
                    System.out.println("Invalid meat choice, must choose 1-6, 99 to go to adding cheeses, or 0 to cancel sandwich order.");
                    Thread.sleep(2500);
                    continue;
            }
            extra = wantExtra(meatType, sandwich.getSize(), "meat");
            sandwich.addTopping(new Meat(meatType, extra));
            if (extra) System.out.println("Added extra " + meatType + " to sandwich.");
            else System.out.println("Added " + meatType + " to sandwich.");
        }
        return sandwich;
    }

    //gets cheese toppings choices
    public static Sandwich addCheese(Sandwich sandwich) throws InterruptedException {
        boolean gettingCheeses = true;
        while(gettingCheeses){
            newScreen();
            String cheeseType;
            System.out.print("        Cheeses ");
            switch (sandwich.getSize()){
                case 4->System.out.println("+$0.75");
                case 8->System.out.println("+$1.50");
                case 12->System.out.println("+$2.25");
            }
            divider(30);
            System.out.println("   1) American");
            System.out.println("   2) Provolone");
            System.out.println("   3) Cheddar");
            System.out.println("   4) Swiss");
            System.out.println("   99) Done with cheeses");
            System.out.println("   0) Cancel");
            switch (getChoice("Enter choice: ")){
                case "1":
                    cheeseType ="American";
                    break;
                case "2":
                    cheeseType ="Provolone";
                    break;
                case "3":
                    cheeseType ="Cheddar";
                    break;
                case "4":
                    cheeseType ="Swiss";
                    break;
                case "99":
                    gettingCheeses = false;
                    continue;
                case "0":
                    gettingCheeses=false;
                    sandwich=null;
                    continue;
                default:
                    System.out.println("Invalid cheese choice, must choose 1-4 to add a cheese, 99 to go to adding freebies, or 0 to cancel sandwich order.");
                    Thread.sleep(2500);
                    continue;
            }
            boolean extra=wantExtra(cheeseType, sandwich.getSize(),"cheese");
            sandwich.addTopping(new Cheese(cheeseType,extra));
            if(extra) System.out.println("Added extra "+ cheeseType +" to sandwich.");
            else System.out.println("Added "+ cheeseType +" to sandwich.");
        }
        return sandwich;
    }

    //gets freebies toppings choices
    public static Sandwich addFreebies(Sandwich sandwich) throws InterruptedException {
        boolean gettingRegular=true;
        while(gettingRegular){
            newScreen();
            String regularType;
            System.out.println("           Freebies");
            divider(30);
            System.out.println("   1) Lettuce");
            System.out.println("   2) Peppers");
            System.out.println("   3) Onions");
            System.out.println("   4) Tomatoes");
            System.out.println("   5) Jalapeños");
            System.out.println("   6) Cucumbers");
            System.out.println("   7) Pickles");
            System.out.println("   8) Guacamole");
            System.out.println("   9) Mushrooms");
            System.out.println("   99) Done with freebies");
            System.out.println("   0) Cancel");
            switch (getChoice("Enter choice: ")){
                case "1":
                    regularType ="Lettuce";
                    break;
                case "2":
                    regularType ="Peppers";
                    break;
                case "3":
                    regularType ="Onions";
                    break;
                case "4":
                    regularType ="Tomatoes";
                    break;
                case "5":
                    regularType ="Jalapeños";
                    break;
                case "6":
                    regularType ="Cucumbers";
                    break;
                case "7":
                    regularType ="Pickles";
                    break;
                case "8":
                    regularType ="Guacamole";
                    break;
                case "9":
                    regularType ="Mushrooms";
                    break;
                case "99":
                    gettingRegular=false;
                    continue;
                case "0":
                    gettingRegular=false;
                    sandwich=null;
                    continue;
                default:
                    System.out.println("Invalid meat choice, must choose 1-9 to add a freebie, 99 to go to adding sauces, or 0 to cancel sandwich order.");
                    Thread.sleep(2500);
                    continue;
            }
            boolean extra=wantExtra(regularType, sandwich.getSize(),"regular");
            sandwich.addTopping(new Topping(regularType,extra));
            if(extra) System.out.println("Added extra "+ regularType +" to sandwich.");
            else System.out.println("Added "+ regularType +" to sandwich.");
        }
        return sandwich;
    }

    //gets sauce toppings choices
    public static Sandwich addSauce(Sandwich sandwich) throws InterruptedException {
        boolean gettingSauces=true;
        while(gettingSauces){
            newScreen();
            String sauceType;
            System.out.println("            Sauces");
            divider(30);
            System.out.println("   1) Mayo");
            System.out.println("   2) Mustard");
            System.out.println("   3) Ketchup");
            System.out.println("   4) Ranch");
            System.out.println("   5) Thousand Island");
            System.out.println("   6) Vinaigrette");
            System.out.println("   99) Done with sauces");
            System.out.println("   0) Cancel");
            switch (getChoice("Enter choice: ")){
                case "1":
                    sauceType ="Mayo";
                    break;
                case "2":
                    sauceType ="Mustard";
                    break;
                case "3":
                    sauceType ="Ketchup";
                    break;
                case "4":
                    sauceType ="Ranch";
                    break;
                case "5":
                    sauceType ="Thousand Island";
                    break;
                case "6":
                    sauceType ="Vinaigrette";
                    break;
                case "99":
                    gettingSauces=false;
                    continue;
                case "0":
                    gettingSauces=false;
                    sandwich=null;
                    continue;
                default:
                    System.out.println("Invalid meat choice, must choose 1-6 to add a sauce, 99 to go to finish adding toppings, or 0 to cancel sandwich order.");
                    Thread.sleep(2500);
                    continue;
            }
            boolean extra=wantExtra(sauceType, sandwich.getSize(),"sauce");
            sandwich.addTopping(new Topping(sauceType,extra));
            if(extra) System.out.println("Added extra "+ sauceType +" to sandwich.");
            else System.out.println("Added "+ sauceType +" to sandwich.");
        }
        return sandwich;
    }

    //gets confirmation that food is correct
    public static boolean confirmFood(Food food){
        newScreen();
        System.out.println(food);
        divider(30);
        if(food instanceof Sandwich){
            if (getChoice("Enter Y if sandwich is correct: ").equalsIgnoreCase("Y")) {
                System.out.println("Adding sandwich to order.");
                return true;
            }
            System.out.println("Did not add sandwich to order.");
            return false;
        } else if(food instanceof Drink drink){
            if(getChoice("Enter Y if drink is correct: ").equalsIgnoreCase("Y")) {
                switch (drink.getSize()){
                    case 1->System.out.println("Added Small "+drink.getFlavor()+" to order.");
                    case 2->System.out.println("Added Medium "+drink.getFlavor()+" to order.");
                    case 3->System.out.println("Added Large "+drink.getFlavor()+" to order.");
                }
                return true;
            }
            System.out.println("Did not add drink to order.");
            return false;
        } else if (food instanceof Chips chips) {
            if(getChoice("Enter Y if chips are correct: ").equalsIgnoreCase("Y")){
                System.out.println("Adding "+chips.getFlavor()+" chips to order.");
                return true;
            }
            System.out.println("Did not add chips to order.");
            return false;
        }
        return true;
    }

    //gets choice of extra toppings or not
    public static boolean wantExtra(String topping, int size, String type){
        if(type.equals("meat")){
            double cost=switch (size){
                case 4->.5;
                case 8->1.;
                case 12->1.5;
                default -> 0;
            };
            return getChoice(String.format("Enter Y for extra %s for $%.2f more: ",topping,cost)).equalsIgnoreCase("Y");
        }
        if(type.equals("cheese")){
            double cost=switch (size){
                case 4->.3;
                case 8->.6;
                case 12->.9;
                default -> 0;
            };
            return getChoice(String.format("Enter Y for extra %s for $%.2f more: ",topping,cost)).equalsIgnoreCase("Y");
        }
        return getChoice("Enter Y for extra " + topping + ": ").equalsIgnoreCase("Y");
    }

    //gets drink order
    public static Drink getDrink() throws InterruptedException {
        Drink drink=null;
        while(true){
            //get drink size
            int size=getDrinkSize();
            //cancel order
            if(size==0) break;
            //get drink flavor
            String flavor=getDrinkFlavor();
            //cancel order
            if(flavor.equals("cancel")) break;
            drink=new Drink(flavor,size);
            //confirm drink order, if wrong ask if user wants to remake
            if(!confirmFood(drink)) {
                if (getChoice("Enter Y to remake drink: ").equalsIgnoreCase("Y")) continue;
            }
            break;
        }
        System.out.println("Returning to order menu.");
        Thread.sleep(2500);
        return drink;
    }

    //gets drink size choice
    public static int getDrinkSize() throws InterruptedException {
        int size=0;
        boolean gettingSize=true;
        while(gettingSize){
            newScreen();
            System.out.println("             Size");
            divider(30);
            System.out.println("   1) Small $2.00");
            System.out.println("   2) Medium $2.50");
            System.out.println("   3) Large $3.00");
            System.out.println("   0) Cancel");
            switch (getChoice("Choose your size: ")){
                case "1":
                    size=1;
                    break;
                case "2":
                    size=2;
                    break;
                case "3":
                    size=3;
                    break;
                case "0":
                    gettingSize=false;
                    continue;
                default:
                    System.out.println("Invalid size choice, must choose 1-3 to pick a size, or 0 to cancel drink order.");
                    Thread.sleep(2500);
                    continue;
            }
            gettingSize=false;
        }
        return size;
    }

    //gets drink flavor choice
    public static String getDrinkFlavor() throws InterruptedException {
        String flavor = "";
        boolean gettingFlavor=true;
        while(gettingFlavor){
            newScreen();
            System.out.println("            Flavor");
            divider(30);
            System.out.println("   1) Coca Cola");
            System.out.println("   2) Diet Coca Cola");
            System.out.println("   3) Coca Cola Zero");
            System.out.println("   4) Fanta");
            System.out.println("   5) Sprite");
            System.out.println("   6) Barq's Root Beer");
            System.out.println("   7) Mountain Dew");
            System.out.println("   8) Dr. Pepper");
            System.out.println("   0) Cancel");
            switch (getChoice("Choose your flavor: ")){
                case "1":
                    flavor="Coca Cola";
                    break;
                case "2":
                    flavor="Diet Coca Cola";
                    break;
                case "3":
                    flavor="Coca Cola Zero";
                    break;
                case "4":
                    flavor="Fanta";
                    break;
                case "5":
                    flavor="Sprite";
                    break;
                case "6":
                    flavor="Barq's Root Beer";
                    break;
                case "7":
                    flavor="Mountain Dew";
                    break;
                case "8":
                    flavor="Dr. Pepper";
                    break;
                case "0":
                    flavor="cancel";
                    gettingFlavor=false;
                    continue;
                default:
                    System.out.println("Invalid flavor choice, must choose 1-8 to pick a flavor, or 0 to cancel drink order.");
                    Thread.sleep(2500);
                    continue;
            }
            gettingFlavor=false;
        }
        return flavor;
    }

    //gets chips order
    public static Chips getChips() throws InterruptedException {
        Chips chips=null;
        while(true){
            //get flavor
            String flavor=getChipsFlavor();
            //cancel order
            if(flavor.equals("Cancel")) break;
            chips=new Chips(flavor);
            //confirm order, if wrong ask if user wants to reorder
            if(!confirmFood(chips)) {
                if (getChoice("Enter Y to reorder chips: ").equalsIgnoreCase("Y")) continue;
            }
            break;
        }
        System.out.println("Returning to order menu.");
        Thread.sleep(2500);
        return chips;
    }

    //gets chips flavor choice
    public static String getChipsFlavor() throws InterruptedException {
        String flavor;
        while(true) {
            newScreen();
            System.out.println("         Chips $1.50");
            divider(30);
            System.out.println("   1) Classic");
            System.out.println("   2) Sour Cream and Onion");
            System.out.println("   3) BBQ");
            System.out.println("   4) Jalapeño");
            System.out.println("   5) Cheetos");
            System.out.println("   6) Fritos");
            System.out.println("   7) Doritos");
            System.out.println("   0) Cancel");
            switch (getChoice("Choose your flavor: ")) {
                case "1":
                    flavor = "Classic";
                    break;
                case "2":
                    flavor = "Sour Cream and Onion";
                    break;
                case "3":
                    flavor = "BBQ";
                    break;
                case "4":
                    flavor = "Jalapeño";
                    break;
                case "5":
                    flavor = "Cheetos";
                    break;
                case "6":
                    flavor = "Fritos";
                    break;
                case "7":
                    flavor = "Doritos";
                    break;
                case "0":
                    flavor = "Cancel";
                    break;
                default:
                    System.out.println("Invalid flavor choice, must choose 1-7 to pick a flavor, or 0 to cancel drink order.");
                    Thread.sleep(2500);
                    continue;
            }
            break;
        }
        return flavor;
    }

    //gets confirmation that order is correct
    public static boolean checkout(Order order){
        newScreen();
        System.out.println(order.getReceipt());
        divider(30);
        if(getChoice("Enter Y if the order is correct: ").equalsIgnoreCase("Y")){
            writeReceipt(order.getReceipt());
            return true;
        }
        else {
            System.out.println("Did not process order.");
            return false;
        }
    }

    //prints string q, waits for response, returns response
    public static String getChoice(String q){
        Scanner input=new Scanner(System.in);
        System.out.print(q);
        return input.nextLine().trim();
    }

    //"clears" CLI
    public static void newScreen(){
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    //custom length divider
    public static void divider(int length){
        System.out.println("-".repeat(length));
    }
}
