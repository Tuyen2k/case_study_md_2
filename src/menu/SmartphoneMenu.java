package menu;

import manage.impl.ParameterManager;
import manage.impl.SmartphoneManager;

import java.util.Scanner;

public class SmartphoneMenu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        SmartphoneManager smartphoneManager = SmartphoneManager.getInstance();
        int choice = -1;
        do {
            System.out.println("Menu Product: ");
            System.out.println("1. Display Product list");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product by id");
            System.out.println("4. Delete Product");
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
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
                ;
            }

        } while (choice != 0);
    }
}
