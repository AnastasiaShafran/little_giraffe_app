package com.example.anastasia.myfirstactivityproject.pojo;

import java.io.Serializable;

/**
 * Created by Anastasia on 4/20/2016.
 */
public class Teacher implements Serializable{
    private String name;
    private String lastName;
    private int age;
    private int phone;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
