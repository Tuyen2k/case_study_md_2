
import manage.impl.RoleManager;
import menu.AccountMenu;
import menu.ParameterMenu;
import menu.RoleMenu;
import menu.SmartphoneMenu;


public class Test {
    public static void main(String[] args) {
        SmartphoneMenu smartphoneMenu = new SmartphoneMenu();
        ParameterMenu parameterMenu = new ParameterMenu();
        RoleMenu roleMenu = new RoleMenu();
        AccountMenu accountMenu = new AccountMenu();


//        parameterMenu.menu();
        smartphoneMenu.menu();

//        roleMenu.menu();
//        accountMenu.menuLogIn();

    }
}
