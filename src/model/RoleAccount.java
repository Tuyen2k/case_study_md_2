package model;

import java.io.Serializable;

public class RoleAccount implements Serializable {
    private static final long serializable = 1234567;
    private int id;
    private String nameRole;

    public static int INDEX = 0;
    public RoleAccount() {
    }

    public RoleAccount(String nameRole) {
        this.id = ++INDEX;
        this.nameRole = nameRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    @Override
    public String toString() {
        return String.format("%-10d %-20s",id,nameRole);
    }
}
