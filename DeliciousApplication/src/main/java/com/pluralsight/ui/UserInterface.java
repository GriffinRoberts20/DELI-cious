package com.pluralsight.ui;

import com.pluralsight.models.*;

import java.util.Scanner;

public class UserInterface {



    public static void display() throws InterruptedException {
        homeMenu();
    }

    public static void homeMenu() throws InterruptedException {

        boolean gettingChoice = true;
        while(gettingChoice){
            newScreen();
            System.out.println("Home Screen");
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

    public static void orderMenu() throws InterruptedException {
        Order order=new Order();
        boolean gettingChoice=true;
        while(gettingChoice){
            newScreen();
            System.out.println("Order Menu");
            divider(30);
            System.out.println("   1) Add Sandwich");
            System.out.println("   2) Add Drink");
            System.out.println("   3) Add Chips");
            System.out.println("   4) Checkout");
            System.out.println("   0) Cancel Order");
            switch (getChoice("Enter choice: ")){
                case "1":
                    Sandwich sandwich=getSandwich();
                    if(sandwich==null) break;
                    order.addFood(sandwich);
                    break;
                case "2":
                    Drink drink=getDrink();
                    if(drink==null) break;
                    order.addFood(drink);
                    break;
                case "3":
                    System.out.println("put add chips here");
                    break;
                case "4":
                    System.out.println("put checkout here");
                    break;
                case "0":
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

    public static Sandwich getSandwich() throws InterruptedException {
        Sandwich sandwich=null;
        boolean gettingSandwich=true;
        boolean cancel=false;
        while(gettingSandwich){
            boolean toasted=false;
            String bread="White";
            int size=4;
            boolean gettingSize=true;
            while(gettingSize){
                newScreen();
                System.out.println("Sizes");
                divider(30);
                System.out.println("   4) Small $5.50");
                System.out.println("   8) Medium $7.00");
                System.out.println("   12) Large $8.50");
                System.out.println("   0) Cancel");
                size=switch (getChoice("Choose your size: ")){
                    case "4"->4;
                    case "8"->8;
                    case "12"->12;
                    case "0"->0;
                    default -> 1;
                };
                if(size==0){
                    cancel=true;
                    break;
                }
                if(size==1){
                    System.out.println("Invalid size choice: must choose  4, 8, or 12, or 0 to cancel.");
                    Thread.sleep(2500);
                    continue;
                }
                gettingSize=false;
            }
            if(cancel) break;
            boolean gettingBread=true;
            while (gettingBread){
                newScreen();
                System.out.println("Breads");
                divider(30);
                System.out.println("   1) White");
                System.out.println("   2) Wheat");
                System.out.println("   3) Rye");
                System.out.println("   4) Wrap");
                System.out.println("   0) Cancel");
                bread=switch (getChoice("Choose your bread: ")){
                    case "1"->"White";
                    case "2"->"Wheat";
                    case "3"->"Rye";
                    case "4"->"Wrap";
                    case "0"->"exit";
                    default -> "invalid";
                };
                if(bread.equals("exit")){
                    cancel=true;
                    break;
                }
                if(bread.equals("invalid")){
                    System.out.println("Invalid bread choice: must choose 1-4, or 0 to cancel");
                    Thread.sleep(2500);
                    continue;
                }
                gettingBread=false;
            }
            if(cancel) break;
            boolean gettingToasted=true;
            while(gettingToasted){
                newScreen();
                System.out.println("Do you want your sandwich toasted?");
                divider(30);
                System.out.println("   1) Yes");
                System.out.println("   2) No");
                System.out.println("   0) Cancel");
                switch (getChoice("Enter choice: ")){
                    case "1":
                        toasted=true;
                        break;
                    case "2":
                        break;
                    case "0":
                        cancel=true;
                        break;
                    default:
                        System.out.println("Invalid toasted choice, must choose 1-2, or 0 to cancel.");
                        Thread.sleep(2500);
                        continue;
                }
                gettingToasted=false;
            }
            if(cancel) break;
            sandwich=new Sandwich(toasted,bread,size);
            boolean gettingToppings=true;
            while(gettingToppings){
                boolean extra;
                boolean gettingMeats=true;
                while(gettingMeats) {
                    newScreen();
                    String meatType;
                    System.out.print("Meats ");
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
                            cancel=true;
                            continue;
                        default:
                            System.out.println("Invalid meat choice, must choose 1-6, 99 to go to adding cheeses, or 0 to cancel sandwich order.");
                            Thread.sleep(2500);
                            continue;
                    }
                    extra=wantExtra(meatType, sandwich.getSize(),"meat");
                    sandwich.addTopping(new Meat(meatType,extra));
                    if(extra) System.out.println("Added extra "+meatType+" to sandwich.");
                    else System.out.println("Added "+meatType+" to sandwich.");
                }
                if(cancel) {
                    sandwich=null;
                    break;
                }
                boolean gettingCheeses=true;
                while(gettingCheeses){
                    newScreen();
                    String cheeseType;
                    System.out.print("Cheeses ");
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
                            gettingCheeses=false;
                            continue;
                        case "0":
                            gettingCheeses=false;
                            cancel=true;
                            continue;
                        default:
                            System.out.println("Invalid cheese choice, must choose 1-4 to add a cheese, 99 to go to adding freebies, or 0 to cancel sandwich order.");
                            Thread.sleep(2500);
                            continue;
                    }
                    extra=wantExtra(cheeseType, sandwich.getSize(),"cheese");
                    sandwich.addTopping(new Cheese(cheeseType,extra));
                    if(extra) System.out.println("Added extra "+ cheeseType +" to sandwich.");
                    else System.out.println("Added "+ cheeseType +" to sandwich.");
                }
                if(cancel) {
                    sandwich=null;
                    break;
                }
                boolean gettingRegular=true;
                while(gettingRegular){
                    newScreen();
                    String regularType;
                    System.out.println("Freebies");
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
                            cancel=true;
                            continue;
                        default:
                            System.out.println("Invalid meat choice, must choose 1-9 to add a freebie, 99 to go to adding sauces, or 0 to cancel sandwich order.");
                            Thread.sleep(2500);
                            continue;
                    }
                    extra=wantExtra(regularType, sandwich.getSize(),"regular");
                    sandwich.addTopping(new Topping(regularType,extra));
                    if(extra) System.out.println("Added extra "+ regularType +" to sandwich.");
                    else System.out.println("Added "+ regularType +" to sandwich.");
                }
                if(cancel) {
                    sandwich=null;
                    break;
                }
                boolean gettingSauces=true;
                while(gettingSauces){
                    newScreen();
                    String sauceType;
                    System.out.println("Sauces");
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
                            cancel=true;
                            continue;
                        default:
                            System.out.println("Invalid meat choice, must choose 1-6 to add a sauce, 99 to go to finish adding toppings, or 0 to cancel sandwich order.");
                            Thread.sleep(2500);
                            continue;
                    }
                    extra=wantExtra(sauceType, sandwich.getSize(),"sauce");
                    sandwich.addTopping(new Topping(sauceType,extra));
                    if(extra) System.out.println("Added extra "+ sauceType +" to sandwich.");
                    else System.out.println("Added "+ sauceType +" to sandwich.");
                }
                if(cancel) {
                    sandwich=null;
                    break;
                }
                gettingToppings=false;
            }
            if(!cancel) {
                newScreen();
                System.out.println(sandwich);
                if (getChoice("Enter Y if sandwich is correct: ").equalsIgnoreCase("Y")) {
                    System.out.println("Adding sandwich to order.");
                    break;
                }
                if (getChoice("Enter Y to remake sandwich: ").equalsIgnoreCase("Y")) {
                    sandwich = null;
                    continue;
                }
                System.out.println("Did not add sandwich to order.");
                sandwich = null;
                gettingSandwich = false;
            }
            else break;
        }
        System.out.println("Returning to order menu.");
        Thread.sleep(2500);
        return sandwich;
    }

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

    public static Drink getDrink() throws InterruptedException {
        Drink drink=null;
        boolean gettingDrink=true;
        boolean cancel=false;
        while(gettingDrink){
            boolean gettingSize=true;
            int size=0;
            String flavor = "";
            while(gettingSize){
                newScreen();
                System.out.println("Size");
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
                        cancel=true;
                        gettingSize=false;
                        continue;
                    default:
                        System.out.println("Invalid size choice, must choose 1-3 to pick a size, or 0 to cancel drink order.");
                        Thread.sleep(2500);
                        continue;
                }
                gettingSize=false;
            }
            if(cancel) break;
            boolean gettingFlavor=true;
            while(gettingFlavor){
                newScreen();
                System.out.println("Flavor");
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
                        cancel=true;
                        gettingFlavor=false;
                        continue;
                    default:
                        System.out.println("Invalid flavor choice, must choose 1-8 to pick a flavor, or 0 to cancel drink order.");
                        Thread.sleep(2500);
                        continue;
                }
                gettingFlavor=false;
            }
            if(!cancel){
                drink=new Drink(flavor,size);
                System.out.println(drink);
                if(getChoice("Enter Y if drink is correct: ").equalsIgnoreCase("Y")) {
                    switch (size){
                        case 1->System.out.println("Added Small "+flavor+" to order.");
                        case 2->System.out.println("Added Medium "+flavor+" to order.");
                        case 3->System.out.println("Added Large "+flavor+" to order.");
                    }
                    break;
                }
                if(getChoice("Enter Y to remake drink: ").equalsIgnoreCase("Y")) continue;
                System.out.println("Did not add drink to order.");
                drink=null;
                gettingDrink=false;
            }
            else break;
        }
        System.out.println("Returning to order menu.");
        Thread.sleep(2500);
        return drink;
    }

    public static String getChoice(String q){
        Scanner input=new Scanner(System.in);
        System.out.print(q);
        return input.nextLine().trim();
    }

    public static void newScreen(){
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void divider(int length){
        System.out.println("-".repeat(length));
    }
}
