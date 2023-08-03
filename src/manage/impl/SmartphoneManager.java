package manage.impl;

import io.FileIO;
import manage.ISmartphoneService;
import model.Category;
import model.Parameter;
import model.Smartphone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SmartphoneManager implements ISmartphoneService {
    private final Scanner scanner;
    private List<Smartphone> smartphones = new ArrayList<>();
    private static SmartphoneManager smartphoneManager;
    private boolean flag;
    private Pattern pattern;
    private int id;
    private final FileIO<Smartphone> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\product";
    private Parameter parameter;
    private final CategoryManager categoryManager = CategoryManager.getInstance();
    private final ParameterManager parameterManager = ParameterManager.getInstance();

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
            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-15s %-20s %-10s %-10s %-10s %-10s\n",
                    "Id", "Name", "Price", "Quantity", "Id Category", "Manufacture", "Series", "Color", "Screen", "CPU", "RAM", "ROM", "Battery", "Machine");
            for (Smartphone smartphone : smartphones) {
                System.out.println(smartphone);
            }
        } else {
            System.out.println("No products in the list!");
        }
    }

    //phần input
    private String getString() {
        int count = 0;
        flag = true;
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
    //kết thúc input

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
        System.out.println("Add product success!!!");
        writeToFile();
    }

    private Smartphone smartphone = new Smartphone();

    //bắt đầu update

    private void editParameterOfProduct() {
        int choice;
        System.out.println("1. Update the existing parameter properties of the product");
        System.out.println("2. Select one available parameters");
        System.out.println("Please choose");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                if (smartphone.getParameter() != null) {
                    parameterManager.parameter = smartphone.getParameter();
                    parameterManager.inputDataUpdate();
                } else {
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
    }

    @Override
    public void update() {
        smartphone = findById();
        if (smartphone != null) {
            System.out.printf("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-10s %-15s %-20s %-10s %-10s %-10s %-10s\n",
                    "Id", "Name", "Price", "Quantity", "Id_Cate", "Manufacture", "Series", "Color", "Screen", "CPU", "RAM", "ROM", "Battery", "Machine");
            System.out.println(smartphone);
            System.out.println("--------------------------------------------------------------");
            System.out.println("Enter property your want update:");
            System.out.printf("%-10d %-10d %-10d %-10d %-10d\n", 1, 2, 3, 4, 5);
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Name", "Price", "Quantity", "Category", "Parameter");

            String choice = scanner.nextLine();
            String str;
            pattern = Pattern.compile("^[12345]$");
            if (pattern.matcher(choice).matches()) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        System.out.println("Enter name product update");
                        str = scanner.nextLine();
                        if (!str.isEmpty()) {
                            smartphone.setName(str);
                        }
                        break;
                    case 2:
                        System.out.println("Enter price product update");
                        str = scanner.nextLine();
                        if (!str.isEmpty()) {
                            smartphone.setPrice(str);
                        }
                        break;
                    case 3:
                        System.out.println("Enter name product update");
                        str = scanner.nextLine();
                        if (!str.isEmpty()) {
                            try {
                                int quantity = Integer.parseInt(str);
                                smartphone.setQuantity(quantity);
                            } catch (NumberFormatException e) {
                                System.out.println("Please enter the correct format!");
                            }
                        }
                        break;
                    case 4:
                        //update category
                        categoryManager.display();
                        Category category = categoryManager.findById();
                        if (category != null) {
                            smartphone.setCategory(category);
                        }
                        break;
                    case 5:
                        //update parameter
                        editParameterOfProduct();
                        break;
                }
                System.out.println("Update product success!!!");
            } else {
                System.out.println("Enter choice correct!!!");
            }
        } else {
            System.out.println("Not found product by id!");
        }
        writeToFile();
    }

    @Override
    public void delete() {
        Smartphone smartphone = findById();
        if (smartphone != null) {
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
        } else {
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

    //tổng tiền trong cửa hàng
    private void totalAmountInStore() {
        double total = 0;
        for (Smartphone smartphone1 : smartphones) {
            total += smartphone1.getQuantity() * Double.parseDouble(smartphone1.getPrice());
        }
        String strNumber = String.format("%010.1f", total);
        System.out.println("Total amount of products in the store: " + strNumber);
    }

    private void totalAmountByCategory() {
        double total = 0;
        categoryManager.display();
        Category category = categoryManager.findById();
        if (category != null) {
            for (Smartphone smartphone1 : smartphones) {
                if (smartphone1.getCategory().equals(category)) {
                    total += smartphone1.getQuantity() * Double.parseDouble(smartphone1.getPrice());
                }
            }
            String strNumber = String.format("%.1f", total);
            System.out.println("Total amount of products by category " + category.getName_category() + " is: " + strNumber);
        } else {
            System.out.println("No products in the matching category!!!");
        }
    }

    public void totalAmountProduct() {
        System.out.println("1. Total amount of products in the store");
        System.out.println("2. Total amount of products by category in the store");
        System.out.println("Enter your choice:");
        String choice = scanner.nextLine();
        pattern = Pattern.compile("^[1,2]$");
        if (pattern.matcher(choice).matches()) {
            if (Integer.parseInt(choice) == 1) {
                totalAmountInStore();
            } else {
                totalAmountByCategory();
            }
        } else {
            System.out.println("Enter the correct option!!!");
        }
    }

    //tổng số lượng sản phẩm trong cửa hàng
    private void totalQuantityProductInStore() {
        int quantity = 0;
        for (Smartphone smartphone1 : smartphones) {
            quantity += smartphone1.getQuantity();
        }
        System.out.println("Total quantity products in the store: " + quantity);
    }

    private void totalQuantityProductByCategory() {
        int quantity = 0;
        categoryManager.display();
        Category category = categoryManager.findById();
        if (category != null) {
            for (Smartphone smartphone1 : smartphones) {
                if (smartphone1.getCategory().equals(category)) {
                    quantity += smartphone1.getQuantity();
                }
            }
            System.out.println("Total quantity of products by category " + category.getName_category() + " is: " + quantity);
        } else {
            System.out.println("No products in the matching category!!!");
        }
    }

    private void totalQuantityProductByName() {
        int quantity = 0;
        String name = inputName();
        if (!name.isEmpty()) {
            for (Smartphone smartphone1 : smartphones) {
                if (smartphone1.getName().equals(name)) {
                    quantity += smartphone1.getQuantity();
                }
            }
            System.out.println("Total quantity of products by " + name + " is: " + quantity);
        } else {
            System.out.println("No products in the matching name your input!!!");
        }
    }

    public void totalQuantityProduct() {
        System.out.println("1. Total quantity of products in the store");
        System.out.println("2. Total quantity of products by category");
        System.out.println("3. Total quantity of products by name");
        System.out.println("Enter your choice:");
        String choice = scanner.nextLine();
        pattern = Pattern.compile("^[123]$");
        if (pattern.matcher(choice).matches()) {
            if (Integer.parseInt(choice) == 1) {
                totalQuantityProductInStore();
            } else if (Integer.parseInt(choice) == 2) {
                totalQuantityProductByCategory();
            } else {
                totalQuantityProductByName();
            }
        } else {
            System.out.println("Enter the correct option!!!");
        }
    }

    //tính năng tìm kiếm

    public String inputDataToFind() {
        String name = scanner.nextLine();

        return name;
    }


    List<Smartphone> list;

    public void searchProduct() {
        list = new ArrayList<>();
        System.out.println("Enter name your want find");
        String name = scanner.nextLine();
        for (Smartphone smartphone1 : smartphones) {
            if (smartphone1.getName().toLowerCase().contains(name.toLowerCase())) {
                list.add(smartphone1);
            }
        }
        if (!list.isEmpty()) {
            for (Smartphone smartphone1 : list) {
                System.out.println(smartphone1);
            }
            System.out.println("-----------------------------------------------");
            inputPriceSearch();
        } else {
            System.out.println("No product match choice!!!");
        }
    }

    private void inputPriceSearch() {
        System.out.println("Enter the price range you want to find");
        pattern = Pattern.compile("^[12345]$");
        System.out.printf("%-20d %-20d %-20d %-20d %-20d\n", 1, 2, 3, 4, 5);
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n",
                "less 15 million", "15-20 million", "20-25 million", "25-30 million", "over 30 million");
        System.out.println("Enter choice");
        String choice = scanner.nextLine();
        if (pattern.matcher(choice).matches()) {
            switch (Integer.parseInt(choice)) {
                case 1:
                    for (Smartphone smartphone1 : smartphones) {
                        if (Double.parseDouble(smartphone1.getPrice()) > 15000000) {
                            list.remove(smartphone1);
                        }
                    }
                    break;
                case 2:
                    for (Smartphone smartphone1 : smartphones) {
                        if (Double.parseDouble(smartphone1.getPrice()) < 15000000 ||
                                Double.parseDouble(smartphone1.getPrice()) > 20000000) {
                            list.remove(smartphone1);
                        }
                    }
                    break;
                case 3:
                    for (Smartphone smartphone1 : smartphones) {
                        if (Double.parseDouble(smartphone1.getPrice()) < 20000000 ||
                                Double.parseDouble(smartphone1.getPrice()) > 25000000) {
                            list.remove(smartphone1);
                        }
                    }
                    break;
                case 4:
                    for (Smartphone smartphone1 : smartphones) {
                        if (Double.parseDouble(smartphone1.getPrice()) < 25000000 ||
                                Double.parseDouble(smartphone1.getPrice()) > 30000000) {
                            list.remove(smartphone1);
                        }
                    }
                    break;
                case 5:
                    for (Smartphone smartphone1 : smartphones) {
                        if (Double.parseDouble(smartphone1.getPrice()) < 30000000) {
                            list.remove(smartphone1);
                        }
                    }
                    break;
            }
        } else {
            System.out.println("Enter the correct option!!!");
        }
        if (!list.isEmpty()) {
            for (Smartphone smartphone1 : list) {
                System.out.println(smartphone1);
            }
            System.out.println("-----------------------------------------------");
            inputRamSearch();
        } else {
            System.out.println("No product match choice!!!");
        }
    }

    private void inputRamSearch() {
        System.out.println("Enter the RAM you want to find");
        String ram = parameterManager.inputRAMChoice();
        if (ram != null){
            Iterator<Smartphone> iterator = list.iterator();
            while (iterator.hasNext()){
                Smartphone smartphone1 = iterator.next();
                if (!smartphone1.getParameter().getRam().equals(ram)){
                    iterator.remove();
                }
            }
            if (!list.isEmpty()) {
                for (Smartphone smartphone1 : list) {
                    System.out.println(smartphone1);
                }
            } else {
                System.out.println("No product match choice!!!");
            }
        }else {
            System.out.println("Enter choice correct!!!");
        }
    }
}
