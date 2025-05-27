package com.pluralsight.ui;

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
            divider(60);
            System.out.println("   1) New Order");
            System.out.println("   0) Exit");
            switch (getChoice("Enter choice: ")) {
                case "1":
                    System.out.println("put order menu here");
                    break;
                case "0":
                    System.out.println("Closing application.");
                    gettingChoice=false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
            Thread.sleep(3000);
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
