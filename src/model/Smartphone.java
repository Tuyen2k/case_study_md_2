package model;

import java.io.Serializable;

public class Smartphone implements Serializable {
    private static final long serialVersionUID = 90083084;
    private int id;
    private String name;
    private String price;
    private int quantity;
    private Category category;
    private Parameter parameter;
    public static int INDEX = 0;

    public Smartphone() {
    }

    public Smartphone( String name, String price, int quantity,Category category, Parameter parameter) {
        this.id = ++INDEX;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.parameter = parameter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-10s %-10d %-10s %-10s", id, name, price,quantity, category, parameter);
    }
}
