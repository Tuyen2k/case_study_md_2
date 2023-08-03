package menu;

import manage.impl.CategoryManager;

import java.util.Scanner;

public class CategoryMenu {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        CategoryManager categoryManager = CategoryManager.getInstance();
        int choice = -1;
        do {
            System.out.println("Menu Category: ");
            System.out.println("1. Display category list");
            System.out.println("2. Add category");
            System.out.println("3. Update category by series");
            System.out.println("4. Delete category");
            System.out.println("0. Exit");
            System.out.println("============================================");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        categoryManager.display();
                        break;
                    case 2:
                        categoryManager.create();
                        break;
                    case 3:
                        categoryManager.update();
                        break;
                    case 4:
                        categoryManager.delete();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }
        } while (choice != 0);
    }

}
