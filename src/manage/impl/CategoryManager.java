package manage.impl;

import io.FileIO;
import manage.ICategoryService;
import model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CategoryManager implements ICategoryService {
    private List<Category> categories = new ArrayList<>() ;
    private final Scanner scanner;
    private static CategoryManager categoryManager;
    private final FileIO<Category> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\category";

    private CategoryManager() {
        scanner = new Scanner(System.in);
        file = new FileIO<>();
        readFromFile();
        setIndex();
    }
    private final List<Category> list = new ArrayList<>();

    private List<Category> getCategories(){
        list.add(new Category("Samsung"));
        list.add(new Category("Apple"));
        list.add(new Category("Xiaomi"));

        return list;
    }
    public static CategoryManager getInstance() {
        if (categoryManager == null) {
            categoryManager = new CategoryManager();
        }
        return categoryManager;
    }

    private void setIndex() {
        if (!categories.isEmpty()) {
            int index = categories.get(0).getId_category();
            for (Category category : categories) {
                if (index < category.getId_category()) {
                    index = category.getId_category();
                }
            }
            Category.INDEX = index;
        } else {
            Category.INDEX = 0;
        }
    }

    @Override
    public void display() {
        if (!categories.isEmpty()) {
            System.out.printf("%-10s %-20s\n", "Id ", "Name Category");
            for (Category category : categories) {
                System.out.println(category);
            }
        } else {
            System.out.println("Not category!");
        }
    }

    private String inputName() {
        System.out.println("Enter name category:");
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

    @Override
    public void create() {
        String name = inputName();
        if (!name.isEmpty()) {
            categories.add(new Category(name));
            System.out.println("Add category success!!!");
        } else {
            System.out.println("Add failed. Please try again!!");
        }

        writeToFile();
    }

    private boolean flag;
    private Pattern pattern;
    private int count;

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

    private int id;

    private boolean checkId() {
        flag = false;
        id = -1;
        try {
            id = Integer.parseInt(inputId());
            for (Category category : categories) {
                if (id == category.getId_category()) {
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
    public Category findById() {
        if (checkId()) {
            for (Category category : categories) {
                if (category.getId_category() == id) {
                    return category;
                }
            }
        }
        return null;
    }

    @Override
    public void update() {
        Category category = findById();
        if (category != null) {
            String name = inputName();
            if (!name.isEmpty()) {
                category.setName_category(name);
            }
            System.out.println("Update success!!!");
        } else {
            System.out.println("No matching id");
        }
        writeToFile();
    }

    @Override
    public void delete() {
        Category category = findById();
        if (category != null) {
            System.out.println(category);
            System.out.println("Are you sure you want to delete?");
            System.out.println("1-Yes/ 0-No");
            pattern = Pattern.compile("^[1,0]$");
            String choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    categories.remove(category);
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

    public void writeToFile(){
        file.writeFile(PATH,categories);
    }

    public void readFromFile(){
        categories = file.readFile(PATH);
        if (categories.isEmpty()){
            categories = getCategories();
        }
    }
}
