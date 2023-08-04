package menu;

import manage.impl.CartManager;
import manage.impl.RoleManager;

import java.util.Scanner;

public class CartMenu {
    public void menu(Scanner scanner, int choice){
        CartManager cartManager = CartManager.getInstance();
        do {
            System.out.println("Menu cart: ");
            System.out.println("1. Display product list in cart");
            System.out.println("2. Add product to cart");
            System.out.println("3. Update quantity product in cart");
            System.out.println("4. Delete product");
            System.out.println("5. Purchase product");
            System.out.println("6. Payment product");
            System.out.println("0. Exit");
            System.out.println("============================================");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        cartManager.display();
                        break;
                    case 2:
                        cartManager.create();
                        break;
                    case 3:
                        cartManager.update();
                        break;
                    case 4:
                        cartManager.delete();
                        break;
                    case 5:
                        cartManager.purchase();
                        break;
                    case 6:
                        cartManager.payProduct();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }
        } while (choice != 0);
    }
}
