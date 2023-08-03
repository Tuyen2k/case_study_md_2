package manage.impl;

import io.FileIO;
import manage.ISmartphoneService;
import model.Category;
import model.Parameter;
import model.Smartphone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SmartphoneManager implements ISmartphoneService {
    private final Scanner scanner;
    private List<Smartphone> smartphones = new ArrayList<>();

    private static SmartphoneManager smartphoneManager;

    private SmartphoneManager() {
        scanner = new Scanner(System.in);
        file = new FileIO<>();
        readFromFile();
        setIndex();
    }

    public static SmartphoneManager getInstance() {
        if (smartphoneManager == null) {
            smartphoneManager = new SmartphoneManager();
        }
        return smartphoneManager;
    }

    private void setIndex() {
        if (!smartphones.isEmpty()) {
            int index = smartphones.get(0).getId();
            for (Smartphone smartphone : smartphones) {
                if (index < smartphone.getId()) {
                    index = smartphone.getId();
                }
            }
            Smartphone.INDEX = index;
        } else {
            Smartphone.INDEX = 0;
        }
    }

    @Override
    public void display() {
        if (!smartphones.isEmpty()) {
            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-10s %-10s %-10s %-10s\n",
                    "Id", "Name", "Price", "Quantity", "Category", "Manufacture", "Series", "Color", "Screen", "CPU", "RAM", "ROM", "Battery", "Machine");
            for (Smartphone smartphone : smartphones) {
                System.out.println(smartphone);
            }
        } else {
            System.out.println("No products in the list!");
        }
    }

    private int count;
    private boolean flag;
    private Pattern pattern;
    private int id;
    private final FileIO<Smartphone> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\product";
    private Parameter parameter;

    private final CategoryManager categoryManager = CategoryManager.getInstance();
    private final ParameterManager parameterManager = ParameterManager.getInstance();

    private void setInput() {
        count = 0;
        flag = true;
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

    private String inputName() {
        setInput();
        System.out.println("Enter name product");
        pattern = Pattern.compile("^.+$");
        return getString();
    }

    private String[] getInput() {
        String[] strings = new String[3];
        strings[0] = inputName();
        System.out.println("Enter price product");
        strings[1] = scanner.nextLine();
        System.out.println("Enter quantity product");
        strings[2] = scanner.nextLine();

        return strings;
    }

    private String inputId() {
        setInput();
        pattern = Pattern.compile("^\\d+$");
        System.out.println("Input id you want: ");
        return getString();
    }

    private boolean checkId() {
        flag = false;
        id = -1;
        try {
            id = Integer.parseInt(inputId());
            for (Smartphone smartphone : smartphones) {
                if (id == smartphone.getId()) {
                    flag = true;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct!!");
        }
        return flag;
    }

    @Override
    public Smartphone findById() {
        if (checkId()) {
            for (Smartphone smartphone : smartphones) {
                if (smartphone.getId() == id) {
                    return smartphone;
                }
            }
        }
        return null;
    }

    private Parameter inputParameter() {
        parameter = null;
        int choice;
        parameterManager.display();
        System.out.println("-----------------------------");
        System.out.println("1. Create new parameters");
        System.out.println("2. Select available parameters");
        System.out.println("Please choose");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                parameterManager.create();
                List<Parameter> parameters = parameterManager.getParameters();
                parameter = parameters.get(parameters.size() - 1);
            } else if (choice == 2) {
                parameter = parameterManager.findById();
            } else {
                System.out.println("Please enter the correct option!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter the correct format!");
        }
        return parameter;
    }

    @Override
    public void create() {
        String[] strings = getInput();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(strings[2]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct format!");
        }
        categoryManager.display();
        Category category = categoryManager.findById();
        parameter = inputParameter();
        smartphones.add(new Smartphone(strings[0], strings[1], quantity, category, parameter));
        writeToFile();
    }

    @Override
    public void update() {
        Smartphone smartphone = findById();
        if (smartphone != null) {
            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-10s %-10s %-10s %-10s\n",
                    "Id", "Name", "Price", "Quantity", "Category", "Manufacture", "Series", "Color", "Screen", "CPU", "RAM", "ROM", "Battery", "Machine");
            System.out.println(smartphone);
            String[] strings = getInput();
            if (!strings[0].isEmpty()) {
                smartphone.setName(strings[0]);
            }
            if (!strings[1].isEmpty()) {
                smartphone.setPrice(strings[1]);
            }
            if (!strings[2].isEmpty()) {
                try {
                    int quantity = Integer.parseInt(strings[2]);
                    smartphone.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter the correct format!");
                }
            }

            categoryManager.display();
            Category category = categoryManager.findById();
            if (category != null) {
                smartphone.setCategory(category);
            }


            int choice;
            System.out.println("1. Update the existing parameter properties of the product");
            System.out.println("2. Select one available parameters");
            System.out.println("Please choose");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    if (smartphone.getParameter() != null){
                        parameterManager.parameter = smartphone.getParameter();
                        parameterManager.inputDataUpdate();
                    }
                    else {
                        System.out.println("No parameters to update!");
                    }
                } else if (choice == 2) {
                    parameterManager.display();
                    parameter = parameterManager.findById();
                    smartphone.setParameter(parameter);
                } else {
                    System.out.println("Please enter the correct option!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the correct format!");
            }
        } else {
            System.out.println("Not found product by id!");
        }
        writeToFile();
    }

    @Override
    public void delete() {
        Smartphone smartphone = findById();
        if (smartphone != null){
            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-20s %-10s %-10s %-10s %-10s\n",
                    "Id", "Name", "Price", "Quantity", "Category", "Manufacture", "Series", "Color", "Screen", "CPU", "RAM", "ROM", "Battery", "Machine");
            System.out.println(smartphone);
            System.out.println("Are you sure you want to delete?");
            System.out.println("1-Yes/ 0-No");
            pattern = Pattern.compile("^[1,0]$");
            String choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    smartphones.remove(smartphone);
                    System.out.println("Success!!!");
                }
            } else {
                System.out.println("Please enter the correct option!! ");
            }
        }
        else {
            System.out.println("Not found parameter by series!");
        }
        writeToFile();
    }

    public void writeToFile() {
        file.writeFile(PATH, smartphones);
    }

    public void readFromFile() {
        smartphones = file.readFile(PATH);
    }


}
