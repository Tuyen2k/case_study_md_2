
import menu.CategoryMenu;
import menu.ParameterMenu;
import menu.SmartphoneMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        SmartphoneMenu smartphoneMenu = new SmartphoneMenu();
        ParameterMenu parameterMenu = new ParameterMenu();
        CategoryMenu categoryMenu = new CategoryMenu();


        int choice = -1;
        do {
            System.out.println("Menu:");
            System.out.println("1. Menu smartphone");
            System.out.println("2. Menu category");
            System.out.println("3. Menu parameter");
            System.out.println("0. Exit");
            System.out.println("************************************************************");
            System.out.println("Enter choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 1:
                    smartphoneMenu.menu();
                    break;
                case 2:
                    categoryMenu.menu();
                    break;
                case 3:
                    parameterMenu.menu();
                    break;
                case 0:
                    System.exit(0);
            }
            System.out.println();
        } while (true);
    }
}
