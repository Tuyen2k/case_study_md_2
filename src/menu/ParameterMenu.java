package menu;

import manage.impl.ParameterManager;

import java.util.Scanner;

public class ParameterMenu {
    public void menu(Scanner scanner, int choice) {
        ParameterManager parameterManager = ParameterManager.getInstance();
        do {
            System.out.println("Menu Parameter: ");
            System.out.println("1. Display parameter list");
            System.out.println("2. Add parameter");
            System.out.println("3. Update parameter by series");
            System.out.println("4. Delete parameter");
            System.out.println("0. Exit");
            System.out.println("============================================");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        parameterManager.display();
                        break;
                    case 2:
                        parameterManager.create();
                        break;
                    case 3:
                        parameterManager.update();
                        break;
                    case 4:
                        parameterManager.delete();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }

        } while (choice != 0);
    }


}
