package manage.impl;

import io.FileIO;
import model.Account;
import model.Bill;
import model.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BillManager {
    private List<Bill> bills = new ArrayList<>();
    private final Scanner scanner;
    private static BillManager billManager;
    private final FileIO<Bill> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\bill";
    private boolean flag;
    private Account account;
    private CartManager cartManager = CartManager.getInstance();

    private BillManager() {
        account = AccountManager.accountLogIn;
        scanner = new Scanner(System.in);
        file = new FileIO<>();
        readFromFile();
    }

    public static BillManager getInstance() {
        if (billManager == null) {
            billManager = new BillManager();
        }
        return billManager;
    }

    private String inputIdProduct() {
        int count = 0;
        flag = false;
        Pattern pattern = Pattern.compile("^\\d+$");
        System.out.println("Input id product you want: ");
        String string = scanner.nextLine();
        while (count < 3 && flag) {
            if (pattern.matcher(string).matches()) {
                flag = false;
            } else {
                count++;
                System.out.println("You have entered invalid " + count + " times");
                string = scanner.nextLine();
            }
        }
        return string;
    }

    private int id;
    private List<Cart> list = cartManager.getCarts();

    private boolean checkStt() {
        flag = false;
        id = -1;
        try {
            id = Integer.parseInt(inputIdProduct());
            for (Cart cart : list) {
                if (id == cart.getId()) {
                    flag = true;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct!!");
        }
        return flag;
    }

    public Cart findById() {
        if (checkStt()) {
            for (Cart cart : list) {
                if (cart.getId() == id) {
                    return cart;
                }
            }
        }
        return null;
    }

    public void writeToFile() {
        file.writeFile(PATH, bills);
    }


    public void readFromFile() {
        bills = file.readFile(PATH);
    }
}
