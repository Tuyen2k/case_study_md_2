package menu;

import manage.impl.RoleManager;

import java.util.Scanner;

public class RoleMenu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        RoleManager roleManager = RoleManager.getInstance();
        int choice = -1;
        do {
            System.out.println("Menu Role: ");
            System.out.println("1. Display role list");
            System.out.println("2. Add new role");
            System.out.println("3. Update role by id");
            System.out.println("4. Delete role");
            System.out.println("0. Exit");
            System.out.println("============================================");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        roleManager.display();
                        break;
                    case 2:
                        roleManager.create();
                        break;
                    case 3:
                        roleManager.update();
                        break;
                    case 4:
                        roleManager.delete();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
                ;
            }
        } while (choice != 0);
    }
}
