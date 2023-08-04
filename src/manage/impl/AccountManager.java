package manage.impl;

import io.FileIO;
import manage.IAccountService;
import model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountManager implements IAccountService {

    private List<Account> accounts = new ArrayList<>();
    private final Scanner scanner;
    private static AccountManager accountManager;
    private final FileIO<Account> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\account";
    private int count;
    private Account account;
    private final RoleManager roleManager = RoleManager.getInstance();

    private int id;
    private boolean flag;
    private Pattern pattern;

    public static Account accountLogIn;
    private AccountManager() {
        scanner = new Scanner(System.in);
        file = new FileIO<>();
        readFromFile();
        setIndex();
    }

    public static AccountManager getInstance() {
        if (accountManager == null) {
            accountManager = new AccountManager();
        }
        return accountManager;
    }

    private List<Account> getAccountsDefault() {
        List<Account> list = new ArrayList<>();
        list.add(new Account("Admin", "tuyen123", "0123456789", roleManager.findById("1")));
        list.add(new Account("Demo", "tuyen123", "0123456789", roleManager.findById("2")));
        return list;
    }

    private void setIndex() {
        if (!accounts.isEmpty()) {
            int index = accounts.get(0).getId();
            for (Account account : accounts) {
                if (index < account.getId()) {
                    index = account.getId();
                }
            }
            Account.INDEX = index;
        } else {
            Account.INDEX = 0;
        }
    }


    @Override
    public void display() {
        if (!accounts.isEmpty()) {
            System.out.printf("%-10s %-20s %-20s %-20s %-10s %-10s\n",
                    "Id ", "User Name", "Password", "Number Phone", "Id Role", "Name Role");
            for (Account account : accounts) {
                System.out.println(account);
            }
        } else {
            System.out.println("Not category!");
        }
    }


    private void setInput() {
        count = 0;
        flag = true;
    }

    private String inputUsername() {
        setInput();
        pattern = Pattern.compile("^[A-Z]\\w{4,15}$");
        System.out.println("Username type: uppercase first character, from 4 to 15 characters, no special characters, no spaces");
        System.out.println("Enter username: ");
        return getString();
    }

    private String inputPassword() {
        setInput();
        pattern = Pattern.compile("^\\w{8,20}$");
        System.out.println("Password type: from 8 to 20 characters, no special characters, no spaces");
        System.out.println("Enter password: ");
        return getString();
    }

    private String inputRe_enterPassword() {
        setInput();
        pattern = Pattern.compile("^\\w{8,20}$");
        System.out.println("Re-Enter password to confirm: ");
        return getString();
    }

    private String inputNumberPhone() {
        setInput();
        pattern = Pattern.compile("^0[^0]\\d{8}$");
        System.out.println("Number phone type:  0973*******");
        System.out.println("Enter number phone: ");
        return getString();
    }


    private String getString() {
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

    private String[] inputData() {
        String[] strings = new String[4];
        strings[0] = inputUsername();
        strings[1] = inputPassword();
        strings[2] = inputRe_enterPassword();
        strings[3] = inputNumberPhone();
        for (String str : strings) {
            if (str.isEmpty()) {
                strings = null;
                break;
            }
        }
        return strings;
    }


    //đăng ký
    @Override
    public void create() {
        String[] strings = inputData();
        if (strings != null) {
            String username = strings[0];
            String password = strings[1];
            String re_password = strings[2];
            String numberPhone = strings[3];
            if (password.equals(re_password)) {
                flag = true;
                for (Account account1 : accounts) {
                    if (account1.getNumberPhone().equals(numberPhone)) {
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    accounts.add(new Account(username, password, numberPhone, roleManager.findById("2")));
                    System.out.println("Sign up success");
                }
                else {
                    System.out.println("Number phone already exists!!!");
                }
            } else {
                System.out.println("Re-entered password is not correct!");
            }
        } else {
            System.out.println("Please enter all data fields!");
        }
        writeToFile();
    }

    private void inputDataUpdate() {
        int choice = -1;
        System.out.printf("%-20d %-20d %-20d\n", 1, 2, 3);
        System.out.printf("%-20s %-20s %-20s\n", "Username", "Password", "Number Phone");
        System.out.printf("%-20s %-20s %-20s\n", account.getUserName(), account.getPassword(), account.getNumberPhone());
        do {
            System.out.println("Enter choice index parameter want update:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter correct!!!");
            }
            switch (choice) {
                case 1:
                    String username = inputUsername();
                    if (!username.isEmpty()) {
                        account.setUserName(username);
                    }
                    break;
                case 2:
                    String password = inputPassword();
                    if (!password.isEmpty()) {
                        account.setPassword(password);
                    }
                    break;
                case 3:
                    String numberPhone = inputNumberPhone();
                    if (!numberPhone.isEmpty()) {
                        account.setNumberPhone(numberPhone);
                    }
                    break;
            }
        } while (choice != 0);
    }

    @Override
    public void update() {
        account = findById();
        if (account != null) {
            inputDataUpdate();
        } else {
            System.out.println("Not found account by id!");
        }
        writeToFile();
    }


    private String inputId() {
        int count = 0;
        flag = false;
        pattern = Pattern.compile("^\\d+$");
        System.out.println("Input id you want: ");
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

    private boolean checkId() {
        flag = false;
        id = -1;
        try {
            id = Integer.parseInt(inputId());
            for (Account account : accounts) {
                if (id == account.getId()) {
                    flag = true;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct!!");
        }
        return flag;
    }


    public String[] inputLogIn() {
        String[] strings = new String[2];
        System.out.println("Enter username:");
        strings[0] = scanner.nextLine();
        System.out.println("Enter password:");
        strings[1] = scanner.nextLine();
        for (String str : strings) {
            if (str.isEmpty()) {
                strings = null;
                break;
            }
        }
        return strings;
    }

    public boolean checkLogin() {
        flag = false;
        String[] strings = inputLogIn();
        if (strings != null) {
            String username = strings[0];
            String password = strings[1];
            for (Account account1 : accounts) {
                if (account1.getUserName().equals(username) && account1.getPassword().equals(password)) {
                    accountLogIn = account1;
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean checkRole() {
        flag = accountLogIn.getRole().getNameRole().equals("Admin");
        return flag;
    }

    @Override
    public Account findById() {
        if (checkId()) {
            for (Account account1 : accounts) {
                if (account1.getId() == id) {
                    return account1;
                }
            }
        }
        return null;
    }


    @Override
    public void delete() {
        Account account = findById();
        if (account != null) {
            System.out.println(account);
            System.out.println("Are you sure you want to delete?");
            System.out.println("1-Yes/ 0-No");
            pattern = Pattern.compile("^[1,0]$");
            String choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    accounts.remove(account);
                    System.out.println("Success!!!");
                }
            } else {
                System.out.println("Please enter the correct option!! ");
            }
        } else {
            System.out.println("Not found category by id");
        }
        writeToFile();
    }

    public void writeToFile() {
        file.writeFile(PATH, accounts);
    }

    public void readFromFile() {
        accounts = file.readFile(PATH);
        if (accounts.isEmpty()) {
            accounts = getAccountsDefault();
        }
    }

}
