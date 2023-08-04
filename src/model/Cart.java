package model;

import java.io.Serializable;

public class Cart implements Serializable {
    private static final long serializable = 1234567;
    private int stt;
    private int id;
    private String nameProduct;
    private String price;
    private int quantity;
    private String paymentAmount;
    private Account account;
    public static  int STT = 0;
    public Cart() {
    }

    public Cart(int id, String nameProduct, String price, int quantity, String paymentAmount, Account account) {
        this.stt = ++STT;
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.paymentAmount = paymentAmount;
        this.account = account;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-20s %-20s %-20d %-20s",stt,id,nameProduct,price,quantity,paymentAmount);
    }

}
