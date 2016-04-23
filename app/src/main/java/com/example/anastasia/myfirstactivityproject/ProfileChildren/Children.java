package com.example.anastasia.myfirstactivityproject.ProfileChildren;

import java.io.Serializable;

/**
 * Created by Anastasia on 4/17/2016.
 */
public class Children implements Serializable {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phone;
    private String momName;
    private String dadName;
    private String emailMom;
    private String emailDad;
    private String group;

    public Children() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMomName() {
        return momName;
    }

    public void setMomName(String momName) {
        this.momName = momName;
    }

    public String getDadName() {
        return dadName;
    }

    public void setDadName(String dadName) {
        this.dadName = dadName;
    }

    public String getEmailMom() {
        return emailMom;
    }

    public void setEmailMom(String emailMom) {
        this.emailMom = emailMom;
    }

    public String getEmailDad() {
        return emailDad;
    }

    public void setEmailDad(String emailDad) {
        this.emailDad = emailDad;
    }
}
