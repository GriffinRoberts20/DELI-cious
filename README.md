# DELI-cious
## Summary
Deli-cious is a console-based sandwich ordering application written in Java. It allows users to customize their sandwiches by selecting size, bread type, toasting preference, and a variety of toppings including meats, cheeses, freebies, and sauces. Users can also order drinks and chips to complete their meal. The app validates input choices, supports order confirmation, and generates a receipt at checkout.

Key features:

- Intuitive menu-driven interface

- Input validation with clear prompts

- Customizable sandwich orders with extra topping options and dynamic pricing

- Drink and chips selection with confirmation

- Order receipt generation and order management

## Application Screens
### Home Screen
Choose whether to start a new order or close the application

![image](https://github.com/user-attachments/assets/28350bb0-05a4-43e0-acb5-7effd33e5638)

### Order Menu
Choose between adding a sandwich, a signature sandwich, a drink, or chips to the order, checking out, or cancelling the order

![image](https://github.com/user-attachments/assets/eca9ad63-5568-43d0-8478-2f534167df01)

### Add Sandwich
Choose the sandwich size

![image](https://github.com/user-attachments/assets/c3849f15-1daf-4c04-8a2b-d717dd70c2e0)

Choose the bread

![image](https://github.com/user-attachments/assets/03e479f5-fc00-4bb4-909b-6fe1daf1b640)

Decide if the sandwich is toasted

![image](https://github.com/user-attachments/assets/65de20b4-d79c-481a-a2a7-ab0ad7c3b780)

Add as many toppings as you want

![image](https://github.com/user-attachments/assets/bbfef4e4-47ad-42dd-bf67-10490b96ec57)

![image](https://github.com/user-attachments/assets/41100c57-38ee-494e-a3d0-7c74d9ed9d81)

![image](https://github.com/user-attachments/assets/5f98959d-3c49-457d-bddd-06902b1a2f6f)

![image](https://github.com/user-attachments/assets/ce447c29-16f1-46b9-8703-1218611df485)

Confirm the sandwich order

![image](https://github.com/user-attachments/assets/4fd2cfec-83b5-491b-8538-04b31b4308f9)

### Add Signature Sandwich
Choose the base sandwich, decide if the sandwich is stacked(extra meat and cheese), and decide whether to remove or add toppings

![image](https://github.com/user-attachments/assets/1a73da5f-557f-4ac5-a7f8-9e2a7433f5b5)

Remove toppings

![image](https://github.com/user-attachments/assets/192ee091-76a2-43da-b214-e4c351570003)

Add toppings, same as a regular sandwich

![image](https://github.com/user-attachments/assets/7b289207-87cc-4c97-8394-6028cefd0000)

Order confirmation is also the same

![image](https://github.com/user-attachments/assets/fbb00242-9a0f-488e-a253-4803934aa4f9)

### Add Drink
Choose drink size

![image](https://github.com/user-attachments/assets/47dc9908-0fc0-4301-a753-309900fa4bc0)

Choose the flavor

![image](https://github.com/user-attachments/assets/ec579775-ab22-4c8e-a790-aae7f423988e)

Confirm drink order

![image](https://github.com/user-attachments/assets/7f7bb4ea-ac0b-40b8-82fd-51a876a91265)

### Add Chips
Choose chips flavor

![image](https://github.com/user-attachments/assets/c5e84b24-e58e-44a5-b462-c3a17c349d8d)

Confirm chips order

![image](https://github.com/user-attachments/assets/2c8cf54f-809d-4f8b-8878-4e160b90a44f)

### Checkout
Confirm order, save receipt if confirmed, ask whether to continue or cancel if not confirmed

![image](https://github.com/user-attachments/assets/79f8031b-f96f-4646-89c4-d7f9fdfea119)

## Interesting Code
The most interesting code I wrote was the method editSignature(), which is where removing toppings from sandwiches is handled. Here, I created a dynamic list to display the current toppings on the sandwich and allow the user to remove toppings one at a time.

```Java
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
```













