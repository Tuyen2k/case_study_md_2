package manage.impl;

import io.FileIO;
import manage.ICartService;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CartManager implements ICartService {
    private List<Cart> carts = new ArrayList<>();
    private final Scanner scanner;
    private static CartManager cartManager;
    private final FileIO<Cart> file;
    private final String PATH = "C:\\Users\\tuyen\\Desktop\\Case_Study_Test\\src\\io\\data\\cart";
    private boolean flag;
    private final Account account;
    private Pattern pattern;
    SmartphoneManager smartphoneManager = SmartphoneManager.getInstance();

    private CartManager() {
        account = AccountManager.accountLogIn;
        scanner = new Scanner(System.in);
        file = new FileIO<>();
        readFromFile();
        setIndex();
    }

    public static CartManager getInstance() {
        if (cartManager == null) {
            cartManager = new CartManager();
        }
        return cartManager;
    }

    private void setIndex() {
        if (!carts.isEmpty()) {
            int stt = carts.get(0).getStt();
            for (Cart cart : carts) {
                if (stt < cart.getStt()) {
                    stt = cart.getStt();
                }
            }
            Cart.STT = stt;
        } else {
            Cart.STT = 0;
        }
    }


    @Override
    public void display() {
        if (!carts.isEmpty()) {
            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s\n",
                    "Id", "ID Product", "Name Product", "Price", "Quantity", "Payment Amount");
            for (Cart cart : carts) {
                System.out.println(cart);
            }
        } else {
            System.out.println("Not product in cart!");
        }
    }

    private int inputQuantityWantBuy() {
        int quantity = 0;
        try {
            System.out.println("Enter quantity your want to buy:");
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter the correct format!!!");
        }
        return quantity;
    }

    private Cart cartIn;

    private boolean checkProductInCart(int id) {
        flag = false;
        cartIn = new Cart();
        for (Cart cart : carts) {
            if (cart.getId() == id) {
                cartIn = cart;
                flag = true;
                break;
            }
        }
        return flag;
    }

    //hiển thị ds search để thêm in cart
    private Smartphone smartphone;

    @Override
    public void create() {
        smartphone = smartphoneManager.findById();
        int idProduct = smartphone.getId();
        String nameProduct = smartphone.getName();
        String price = smartphone.getPrice();

        if (checkProductInCart(idProduct)) {    //trường hợp sp đã có trong cart
            int quantity = inputQuantityWantBuy() + cartIn.getQuantity();
            if (quantity != 0 && quantity <= smartphone.getQuantity()) {
                cartIn.setQuantity(quantity);
                double payment = Double.parseDouble(cartIn.getPrice()) * quantity;
                String strPayment = String.format("%.1f", payment);
                cartIn.setPaymentAmount(strPayment);
                System.out.println("Add product success!!!");
            } else {
                System.out.println("Can't add products due to unreasonable quantity!!!");
            }
        } else {   //trường hợp sp chưa có trong cart
            int quantity = inputQuantityWantBuy();
            if (quantity != 0 && quantity <= smartphone.getQuantity()) {
                double payment = Double.parseDouble(price) * quantity;
                String strPayment = String.format("%.1f", payment);
                carts.add(new Cart(idProduct, nameProduct, price, quantity, strPayment, account));
                System.out.println("Add product success!!!");
            } else {
                System.out.println("Can't add products due to unreasonable quantity!!!");
            }
        }
        writeToFile();
    }

    @Override
    public void update() {
        display();
        Cart cart = findById();
        if (cart != null) {
            for (Smartphone smartphone1 : smartphoneManager.getSmartphones()) {
                if (smartphone1.getId() == cart.getId()) {
                    smartphone = smartphone1;
                }
            }
            int quantity = inputQuantityWantBuy();
            if ((quantity != 0 && quantity <= smartphone.getQuantity())) {
                double payment = Double.parseDouble(cart.getPrice()) * quantity;
                String strPayment = String.format("%.1f", payment);
                cart.setQuantity(quantity);
                cart.setPaymentAmount(strPayment);
                System.out.println("Update product success!!!");
            } else {
                System.out.println("Can't add products due to unreasonable quantity!!!");
            }
        } else {
            System.out.println("No product in cart!!!");
        }
        writeToFile();
    }


    private String inputStt() {
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

    private int id;

    private boolean checkStt() {
        flag = false;
        id = -1;
        try {
            id = Integer.parseInt(inputStt());
            for (Cart cart : carts) {
                if (id == cart.getStt()) {
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
    public Cart findById() {
        if (checkStt()) {
            for (Cart cart : carts) {
                if (cart.getStt() == id) {
                    return cart;
                }
            }
        }
        return null;
    }

    @Override
    public void delete() {
        Cart cart = findById();
        if (cart != null) {
            System.out.println(cart);
            System.out.println("Are you sure you want to delete?");
            System.out.println("1-Yes/ 0-No");
            pattern = Pattern.compile("^[1,0]$");
            String choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    carts.remove(cart);
                    System.out.println("Success!!!");
                }
            } else {
                System.out.println("Please enter the correct option!! ");
            }
        }
        writeToFile();
    }

    public void writeToFile() {
        file.writeFile(PATH, carts);
    }

    public void readFromFile() {
        carts = file.readFile(PATH);
    }

    private List<Cart> bills = new ArrayList<>();

    public void purchase() {
        display();
        Cart cart = findById();
        if (cart != null) {
            if (!bills.isEmpty()) {
                flag = false;
                for (Cart cart1 : bills) {
                    if (cart1.getId() != cart.getId()) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    bills.add(cart);
                    System.out.println("Add bill success!!!");
                } else {
                    System.out.println("The product is already in bill!!!");
                }
            } else {
                bills.add(cart);
                System.out.println("Add bill success!!!");
            }
        } else {
            System.out.println("No product as your entered!!!");
        }
//        file.writeFile(PATH_PRODUCT_PURCHASE,bills);
        writeToFile();
    }

    public void displayProductInBill() {
        if (!bills.isEmpty()) {
            System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s\n",
                    "Id", "ID Product", "Name Product", "Price", "Quantity", "Payment Amount");
            for (Cart bill : bills) {
                System.out.println(bill);
            }
        } else {
            System.out.println("Please select the product to pay!!!");
        }
    }

    public void payProduct() {
        if (!bills.isEmpty()) {
            displayProductInBill();
            System.out.println("You want to buy the product?");
            System.out.println("1-Yes/ 0-No");
            pattern = Pattern.compile("^[1,0]$");
            String choice = scanner.nextLine();
            if (pattern.matcher(choice).matches()) {
                if (Integer.parseInt(choice) == 1) {
                    listProductPurchase.addAll(bills);
                    carts.removeAll(bills);
                    bills = new ArrayList<>();
                    System.out.println("Success!!!");
                    productInStock();
                }
            } else {
                System.out.println("Please enter the correct option!! ");
            }
        } else {
            System.out.println("Please select the product to pay!!!");
        }
    }

    private List<Cart> listProductPurchase = new ArrayList<>();
    private final List<Cart> listProductTemp = new ArrayList<>();

    private void productInStock(){
        List<Smartphone> smartphones = smartphoneManager.getSmartphones();
        int quantityAfterPurchase;
        for (Smartphone smartphone1 : smartphones){
            for (Cart cart : listProductPurchase){
                if (smartphone1.getId() == cart.getId()){
                    quantityAfterPurchase = smartphone1.getQuantity() - cart.getQuantity();
                    if (quantityAfterPurchase != -1){
                        smartphone1.setQuantity(quantityAfterPurchase);
                    }
                }
            }
        }
        listProductTemp.addAll(listProductPurchase);
        smartphoneManager.writeToFile();
        listProductPurchase = new ArrayList<>();
    }

    public void totalRevenue(){
        double total = 0;
        for (Cart cart : listProductTemp){
            total += cart.getQuantity() * Double.parseDouble(cart.getPrice());
        }
        String strTotal = String.format("%.1f",total);
        System.out.println("Total sales revenue " + strTotal);
    }

}
