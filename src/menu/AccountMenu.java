package menu;

import manage.impl.AccountManager;

import java.util.Scanner;

public class AccountMenu {

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = AccountManager.getInstance();
        int choice = -1;
        do {
            System.out.println("1. Log In!!!");
            System.out.println("2. Sign In!!!");
            System.out.println("0. Exit!");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        if (accountManager.checkLogin()){
                            System.out.println("Log in successful!");
                            //thiết kế menu cho từng role
                            if (accountManager.checkRole()){
                                System.out.println("Admin");
                            }
                            else {
                                System.out.println("User");
                            }
                        }
                        else {
                            System.out.println("Log in unsuccessful");
                        }
                        break;
                    case 2:
                        accountManager.create();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }

        } while (choice != 0);
    }
}
