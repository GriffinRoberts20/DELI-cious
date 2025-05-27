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
            if(gettingChoice) Thread.sleep(3000);
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
                    System.out.println("put add sandwich here");
                    break;
                case "2":
                    System.out.println("put add drink here");
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
            if(gettingChoice) Thread.sleep(3000);
        }
    }

    public static String getChoice(String q){
        Scanner input=new Scanner(System.in);
        System.out.print(q);
        return input.nextLine();
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
