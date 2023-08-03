package menu;

import manage.impl.AccountManager;
import manage.impl.SmartphoneManager;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountMenu {

    public void menuLogIn() {
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
                        if (accountManager.checkLogin()) {
                            System.out.println("Log in successful!");
                            //thiết kế menu cho từng role
                            if (accountManager.checkRole()) {
                                menuAdmin(scanner,choice);
                            } else {
                                menuUser(scanner,choice);
                            }
                        } else {
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
            System.out.println("************************================================");
        } while (choice != 0);
    }

    public void menuAdmin(Scanner scanner, int choice) {
        SmartphoneMenu smartphoneMenu = new SmartphoneMenu();
        CategoryMenu categoryMenu = new CategoryMenu();
        ParameterMenu parameterMenu = new ParameterMenu();
        RoleMenu roleMenu = new RoleMenu();

        do {
            System.out.println("Menu admin:");
            System.out.println("1. Menu product:");
            System.out.println("2. Menu category:");
            System.out.println("3. Menu parameter:");
            System.out.println("4. Menu account:");
            System.out.println("5. Menu role:");
            System.out.println("0. Exit:");
            System.out.println("******************************************");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        smartphoneMenu.menu(scanner,choice);
                        break;
                    case 2:
                        categoryMenu.menu(scanner,choice);
                        break;
                    case 3:
                        parameterMenu.menu(scanner,choice);
                        break;
                    case 4:
                        menuAccount(scanner,choice);
                        break;
                    case 5:
                        roleMenu.menu(scanner,choice);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }
        } while (choice != 0);
    }

    public void menuAccount(Scanner scanner, int choice){
        AccountManager accountManager = AccountManager.getInstance();
        do {
            System.out.println("Menu account: ");
            System.out.println("1. Display account list");
            System.out.println("2. Add new account");
            System.out.println("3. Update account by id");
            System.out.println("4. Delete account");
            System.out.println("0. Exit");
            System.out.println("============================================");
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        accountManager.display();
                        break;
                    case 2:
                        accountManager.create();
                        break;
                    case 3:
                        accountManager.update();
                        break;
                    case 4:
                        accountManager.delete();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }
        } while (choice != 0);
    }

    public void menuUser(Scanner scanner, int choice){
        SmartphoneManager smartphoneManager = SmartphoneManager.getInstance();
        List<Smartphone> list1 = new ArrayList<>();
        do {
            System.out.println("Menu user: ");
            System.out.println("1. Display product in store");
            System.out.println("2. Search product by category, name, price and ram");
            System.out.println("3. Search product by name");
            System.out.println("4. Sort products by price increase ");
            System.out.println("5. Sort products by price decrease ");
//bổ sung tính năng user: giỏ hàng và thanh toán
            System.out.println("0. Exit");
            System.out.println("====================================");
            System.out.println("Enter choice");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        smartphoneManager.display();
                        break;
                    case 2:
                        smartphoneManager.searchProduct();
                        list1 = smartphoneManager.getList();
                        break;
                    case 3:
                        smartphoneManager.searchName();
                        list1 = smartphoneManager.getList();
                        break;
                    case 4:
                        if (list1.isEmpty()) {
                            list1 = smartphoneManager.getSmartphones();
                        }
                        smartphoneManager.displayByPriceIncrease(list1);
                        break;
                    case 5:
                        if (list1.isEmpty()) {
                            list1 = smartphoneManager.getSmartphones();
                        }
                        smartphoneManager.displayByPriceDecrease(list1);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter correct!!");
            }
        }while (choice != 0);
    }
}
