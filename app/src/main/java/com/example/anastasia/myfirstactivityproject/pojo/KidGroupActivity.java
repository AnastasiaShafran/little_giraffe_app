package com.example.anastasia.myfirstactivityproject.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tal on 08/05/2016.
 */
public class KidGroupActivity implements Serializable{
    Map<String,KidActivityForDate> kidsActivity;

    public KidGroupActivity(Map<String, KidActivityForDate> kidsActivity) {
        this.kidsActivity = kidsActivity;
    }

    public KidGroupActivity() {
        this.kidsActivity = new HashMap<>();
    }

    public Map<String, KidActivityForDate> getKidsActivity() {
        return kidsActivity;
    }

    public void setKidsActivity(Map<String, KidActivityForDate> kidsActivity) {
        this.kidsActivity = kidsActivity;
    }

}
