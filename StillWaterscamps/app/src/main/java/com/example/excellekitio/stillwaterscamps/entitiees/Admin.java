package com.example.excellekitio.stillwaterscamps.entitiees;

/**
 * Created by lesli on 13/12/2017.
 */

public class Admin {
    private int id;
    private String password;
    private String name;

    public Admin() {
    }

    public Admin(int id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
