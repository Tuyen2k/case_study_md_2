package menu;

import manage.impl.SmartphoneManager;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmartphoneMenu {
    public void menu(Scanner scanner, int choice) {
        SmartphoneManager smartphoneManager = SmartphoneManager.getInstance();
        do {
            System.out.println("Menu Product: ");
            System.out.println("1. Display Product list");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product by id");
            System.out.println("4. Delete Product");
            System.out.println("5. Total amount of products");
            System.out.println("6. Total quantity of products");
            System.out.println("7. Search product product");
            System.out.println("0. Exit");
            System.out.println("============================================");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        smartphoneManager.display();
                        break;
                    case 2:
                        smartphoneManager.create();
                        break;
                    case 3:
                        smartphoneManager.update();
                        break;
                    case 4:
                        smartphoneManager.delete();
                        break;
                    case 5:
                        smartphoneManager.totalAmountProduct();
                        break;
                    case 6:
                        smartphoneManager.totalQuantityProduct();
                        break;
                    case 7:
                        smartphoneManager.searchProduct();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }
        } while (choice != 0);
    }
}
