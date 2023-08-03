package manage.impl;

import io.FileIO;
import model.RoleAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RoleManager {
    private List<RoleAccount> roleAccounts = new ArrayList<>();
    private final Scanner scanner;
    private static RoleManager roleManager;
    private final FileIO<RoleAccount> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\role";
    private boolean flag;
    private int count;
    private Pattern pattern;
    private RoleManager() {
        scanner = new Scanner(System.in);
        file = new FileIO<>();
        readFromFile();
        setIndex();
    }
    public static RoleManager getInstance(){
        if (roleManager == null){
            roleManager = new RoleManager();
        }
        return roleManager;
    }
    private List<RoleAccount> getRoleAccountsDefault(){
        List<RoleAccount> list = new ArrayList<>();
        list.add(new RoleAccount("Admin"));
        list.add(new RoleAccount("User"));
        return list;
    }
    private void setIndex() {
        if (!roleAccounts.isEmpty()) {
            int index = roleAccounts.get(0).getId();
            for (RoleAccount role : roleAccounts) {
                if (index < role.getId()) {
                    index = role.getId();
                }
            }
            RoleAccount.INDEX = index;
        } else {
            RoleAccount.INDEX = 0;
        }
    }

    public void display() {
        if (!roleAccounts.isEmpty()) {
            System.out.printf("%-10s %-20s\n", "Id ", "Name role");
            for (RoleAccount roleAccount : roleAccounts) {
                System.out.println(roleAccount);
            }
        } else {
            System.out.println("Not role!");
        }
    }

    private String inputName() {
        System.out.println("Enter name role:");
        String name = scanner.nextLine();
        count = 0;
        while (count < 3 && name.isEmpty()) {
            count++;
            System.out.println("You input error " + count + " times!");
            System.out.println("Please re-enter:");
            name = scanner.nextLine();
        }
        return name;
    }


    public void create() {
        String name = inputName();
        if (!name.isEmpty()) {
            roleAccounts.add(new RoleAccount(name));
            System.out.println("Add role success!!!");
        } else {
            System.out.println("Add failed. Please try again!!");
        }

        writeToFile();
    }


    public void update() {
        RoleAccount roleAccount = findById(inputId());
        if (roleAccount != null) {
            String name = inputName();
            if (!name.isEmpty()) {
                roleAccount.setNameRole(name);
            }
            System.out.println("Update success!!!");
        } else {
            System.out.println("No matching id");
        }
        writeToFile();
    }

    private String inputId() {
        count = 0;
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
    private boolean checkId(String id) {
        flag = false;
        try {
            int index = Integer.parseInt(id);
            for (RoleAccount roleAccount : roleAccounts) {
                if (index == roleAccount.getId()) {
                    flag = true;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct!!");
        }
        return flag;
    }


    public RoleAccount findById(String id) {
        RoleAccount role = new RoleAccount();
        if (checkId(id)) {
            for (RoleAccount roleAccount : roleAccounts) {
                if (roleAccount.getId() == Integer.parseInt(id)) {
                    role = roleAccount;
                }
            }
        }
        return role;
    }



    public void delete() {
        RoleAccount roleAccount = findById(inputId());
        if (roleAccount != null) {
            System.out.println(roleAccount);
            System.out.println("Are you sure you want to delete?");
            System.out.println("1-Yes/ 0-No");
            pattern = Pattern.compile("^[1,0]$");
            String choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    if (!roleAccount.getNameRole().equals("Admin")){
                        roleAccounts.remove(roleAccount);
                        System.out.println("Success!!!");
                    }else {
                        System.out.println("Can't delete admin role");
                    }
                }
            } else {
                System.out.println("Please enter the correct option!! ");
            }
        } else {
            System.out.println("Not found role by id");
        }
        writeToFile();
    }

    public void writeToFile(){
        file.writeFile(PATH,roleAccounts);
    }
    public void readFromFile(){
        roleAccounts = file.readFile(PATH);
        if (roleAccounts.isEmpty()){
            roleAccounts = getRoleAccountsDefault();
        }
    }
}
