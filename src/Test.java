
import menu.*;

import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        SmartphoneMenu smartphoneMenu = new SmartphoneMenu();
        ParameterMenu parameterMenu = new ParameterMenu();
        RoleMenu roleMenu = new RoleMenu();
        AccountMenu accountMenu = new AccountMenu();

        accountMenu.menuLogIn();

    }
}
