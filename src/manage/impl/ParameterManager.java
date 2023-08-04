package manage.impl;

import io.FileIO;
import manage.IParameterService;
import model.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ParameterManager implements IParameterService {
    private final Scanner scanner;
    private List<Parameter> parameters = new ArrayList<>();
    private static ParameterManager parameterManager;
    private final FileIO<Parameter> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\parameter";
    private boolean flag;
    private Pattern pattern;

    private ParameterManager() {
        scanner = new Scanner(System.in);
        file = new FileIO<>();
        readFromFile();
        setIndex();
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public static ParameterManager getInstance() {
        if (parameterManager == null) {
            parameterManager = new ParameterManager();
        }
        return parameterManager;
    }

    private final List<Parameter> list = new ArrayList<>();

    private List<Parameter> getList() {
        list.add(new Parameter("black", "IPS LCD", "Snapdragon 800", "6", "128", "5000", "100"));
        list.add(new Parameter("white", "AMOLED", "Snapdragon 755", "4", "128", "5000", "99"));
        list.add(new Parameter("pink", "Super AMOLED", "Snapdragon 850", "8", "128", "5000", "100"));
        list.add(new Parameter("black", "AMOLED 2X", "A15 Bionic", "4", "128", "4000", "100"));
        list.add(new Parameter("gold", "AMOLED 2X", "A15 Bionic", "6", "128", "5000", "100"));
        list.add(new Parameter("blue", "AMOLED", "A14 Bionic", "8", "256", "5000", "99"));

        return list;
    }

    private void setIndex() {
        if (!parameters.isEmpty()) {
            int index = parameters.get(0).getSeries();
            for (Parameter parameter : parameters) {
                if (index < parameter.getSeries()) {
                    index = parameter.getSeries();
                }
            }
            Parameter.INDEX = index;
        } else {
            Parameter.INDEX = 0;
        }
    }


    @Override
    public void display() {
        if (!parameters.isEmpty()) {
            System.out.printf("%-10s %-10s %-15s %-20s %-10s %-10s %-10s %-10s\n",
                    "Series", "Color", "Screen", "CPU", "RAM", "ROM", "Battery", "Machine");
            for (Parameter parameter : parameters) {
                System.out.println(parameter);
            }
        } else {
            System.out.println("Not parameter!");
        }
    }

    //bắt đầu method nhập thông tin
    private String inputColorChoice() {
        String str = null;
        int choice;
        System.out.println("--------------------------------------");
        System.out.println("Choose the color of the product ");
        System.out.printf("%-20d %-20d %-20d %-20d %-20d %-20d\n", 1, 2, 3, 4, 5, 6);
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", "red", "blue", "green", "pink", "black", "white");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    str = "red";
                    break;
                case 2:
                    str = "blue";
                    break;
                case 3:
                    str = "green";
                    break;
                case 4:
                    str = "pink";
                    break;
                case 5:
                    str = "black";
                    break;
                case 6:
                    str = "white";
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please enter the appropriate option");
        }
        return str;
    }

    private String inputScreenChoice() {
        String str = null;
        int choice;
        System.out.println("--------------------------------------");
        System.out.println("Choose the screen technology of the product ");
        System.out.printf("%-20d %-20d %-20d %-20d %-20d\n", 1, 2, 3, 4, 5);
        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", "IPS LCD", "OLED", "AMOLED", "Super AMOLED", "Dynamic AMOLED");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    str = "IPS LCD";
                    break;
                case 2:
                    str = "OLED";
                    break;
                case 3:
                    str = "AMOLED";
                    break;
                case 4:
                    str = "Super AMOLED";
                    break;
                case 5:
                    str = "Dynamic AMOLED";
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please enter the appropriate option");
        }
        return str;
    }

    private String inputCpuChoice() {
        String str = null;
        int choice;
        System.out.println("--------------------------------------");
        System.out.println("Choose the CPU of the product ");
        System.out.printf("%-20d %-20d %-20d %-20d %-20d %-20d\n", 1, 2, 3, 4, 5, 6);
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", "Apple A15", "Apple A14", "Snapdragon 800", "Snapdragon 810", "Snapdragon 755", "Snapdragon 850");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    str = "Apple A15";
                    break;
                case 2:
                    str = "Apple A14";
                    break;
                case 3:
                    str = "Snapdragon 800";
                    break;
                case 4:
                    str = "Snapdragon 810";
                    break;
                case 5:
                    str = "Snapdragon 755";
                    break;
                case 6:
                    str = "Snapdragon 850";
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please enter the appropriate option");
        }
        return str;
    }

    public String inputRAMChoice() {
        String str = null;
        int choice;
        System.out.printf("%-20d %-20d %-20d %-20d\n", 1, 2, 3, 4);
        System.out.printf("%-20s %-20s %-20s %-20s\n", "4GB", "6GB", "8GB", "16GB");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    str = "4";
                    break;
                case 2:
                    str = "6";
                    break;
                case 3:
                    str = "8";
                    break;
                case 4:
                    str = "16";
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please enter the appropriate option");
        }
        return str;
    }

    private String inputROMChoice() {
        String str = null;
        int choice;
        System.out.println("--------------------------------------");
        System.out.println("Choose the ROM of the product ");
        System.out.printf("%-20d %-20d %-20d %-20d\n", 1, 2, 3, 4);
        System.out.printf("%-20s %-20s %-20s %-20s\n", "64GB", "128GB", "256GB", "512GB");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    str = "64";
                    break;
                case 2:
                    str = "128";
                    break;
                case 3:
                    str = "256";
                    break;
                case 4:
                    str = "512";
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please enter the appropriate option");
        }
        return str;
    }

    private String inputBatteryChoice() {
        String str = null;
        int choice;
        System.out.println("--------------------------------------");
        System.out.println("Choose the battery of the product ");
        System.out.printf("%-20d %-20d %-20d %-20d\n", 1, 2, 3, 4);
        System.out.printf("%-20s %-20s %-20s %-20s\n", "3900mAh", "4500mAh", "5000mAh", "6000mAh");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    str = "3900";
                    break;
                case 2:
                    str = "4500";
                    break;
                case 3:
                    str = "5000";
                    break;
                case 4:
                    str = "6000";
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please enter the appropriate option");
        }
        return str;
    }

    private String inputMachineChoice() {
        String str = "100";
        int choice;
        System.out.println("--------------------------------------");
        System.out.println("Choose the machine status of the product ");
        System.out.printf("%-20d %-20d\n", 1, 2);
        System.out.printf("%-20s %-20s\n", "100%", "99%");
        try {
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    str = "100";
                    break;
                case 2:
                    str = "99";
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please enter the appropriate option");
        }
        return str;
    }


    //bắt đầu thêm mới

    @Override
    public void create() {
        String color = inputColorChoice();
        String screen = inputScreenChoice();
        String cpu = inputCpuChoice();
        String ram = inputRAMChoice();
        String rom = inputROMChoice();
        String battery = inputBatteryChoice();
        String machine = inputMachineChoice();
        parameters.add(new Parameter(color, screen, cpu, ram, rom, battery, machine));
        writeToFile();
    }

    //hết thêm mới

    // bắt đầu update
    public void inputDataUpdate() {
        int choice = -1;
        System.out.printf("%-10s %-10d %-15d %-20d %-10d %-10d %-10d %-30d\n", "0. Exit ", 1, 2, 3, 4, 5, 6, 7);
        System.out.printf("%-10s %-10s %-15s %-20s %-10s %-10s %-10s %-30s\n", "Series", "Color", "Screen", "CPU", "RAM", "ROM", "PIN", "Machine");
        System.out.println(parameter);
        do {
            System.out.println("Enter your choice:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter correct!!!");
            }
            switch (choice) {
                case 1:
                    String color = inputColorChoice();
                    if (color != null) {
                        parameter.setColor(color);
                    }
                    break;
                case 2:
                    String screen = inputScreenChoice();
                    if (screen != null) {
                        parameter.setScreenTechnology(screen);
                    }
                    break;
                case 3:
                    String cpu = inputCpuChoice();
                    if (cpu != null) {
                        parameter.setCpu(cpu);
                    }
                    break;
                case 4:
                    String ram = inputRAMChoice();
                    if (ram != null) {
                        parameter.setRam(ram);
                    }
                    break;
                case 5:
                    String rom = inputROMChoice();
                    if (rom != null) {
                        parameter.setRom(rom);
                    }
                    break;
                case 6:
                    String battery = inputBatteryChoice();
                    if (battery != null) {
                        parameter.setBattery(battery);
                    }
                    break;
                case 7:
                    String machine = inputMachineChoice();
                    parameter.setMachineStatus(machine);
                    break;
            }
        } while (choice != 0);
    }

    Parameter parameter;


    private String inputId() {
        int count = 0;
        flag = true;
        pattern = Pattern.compile("^\\d+$");
        System.out.println("Input series you want: ");
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
            for (Parameter parameter1 : parameters) {
                if (id == parameter1.getSeries()) {
                    flag = true;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct data parameter!!");
        }
        return flag;
    }

    @Override
    public void update() {
        parameter = findById();
        if (parameter != null) {
            inputDataUpdate();
        } else {
            System.out.println("Not found parameter by series!");
        }
        writeToFile();
    }

    //kết thúc update

    @Override
    public Parameter findById() {
        Parameter parameter = new Parameter();
        if (checkId()) {
            for (Parameter parameter1 : parameters) {
                if (parameter1.getSeries() == id) {
                    parameter = parameter1;
                }
            }
        }
        return parameter;
    }


    @Override
    public void delete() {
        parameter = findById();
        if (parameter != null) {
            System.out.println(parameter);
            System.out.println("Are you sure you want to delete?");
            System.out.println("1-Yes/ 0-No");
            pattern = Pattern.compile("^[1,0]$");
            String choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    parameters.remove(parameter);
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
        file.writeFile(PATH, parameters);
    }

    public void readFromFile() {
        parameters = file.readFile(PATH);
        if (parameters.isEmpty()) {
            parameters = getList();
        }
    }

}
