package com.example.anastasia.myfirstactivityproject.pojo;

import java.io.Serializable;

/**
 * Created by Tal on 08/05/2016.
 */
public class KidActivityForDate implements Serializable{
    boolean arrived;
    boolean breakfast;
    boolean morningSleep;
    boolean lunch;
    boolean afternoonSleep;
    boolean fecal;

    public KidActivityForDate(boolean arrived, boolean breakfast, boolean morningSleep, boolean lunch, boolean afternoonSleep, boolean fecal) {
        this.arrived = arrived;
        this.breakfast = breakfast;
        this.morningSleep = morningSleep;
        this.lunch = lunch;
        this.afternoonSleep = afternoonSleep;
        this.fecal = fecal;
    }

    public KidActivityForDate(){};

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isMorningSleep() {
        return morningSleep;
    }

    public void setMorningSleep(boolean morningSleep) {
        this.morningSleep = morningSleep;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean isAfternoonSleep() {
        return afternoonSleep;
    }

    public void setAfternoonSleep(boolean afternoonSleep) {
        this.afternoonSleep = afternoonSleep;
    }

    public boolean isFecal() {
        return fecal;
    }

    public void setFecal(boolean fecal) {
        this.fecal = fecal;
    }
}
