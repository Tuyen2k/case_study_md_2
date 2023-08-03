package model;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serializable = 1234567;
    private int id;
    private String userName;
    private String password;
    private String numberPhone;
    private RoleAccount role;

    public static int INDEX = 0;

    public Account() {
    }

    public Account(String userName, String password,String numberPhone, RoleAccount role) {
        this.id = ++INDEX;
        this.userName = userName;
        this.password = password;
        this.numberPhone = numberPhone;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public RoleAccount getRole() {
        return role;
    }

    public void setRole(RoleAccount role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s %-20s %-20s %-20s", id, userName, password, numberPhone, role);
    }
}
