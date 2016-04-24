package com.example.anastasia.myfirstactivityproject.pojo;

import java.util.HashMap;


public class TeacherSchedule {
    HashMap<String,String > mapDayToGroup;

    public TeacherSchedule() {
        mapDayToGroup = new HashMap<>();
    }

    public void setMapDayToGroup(HashMap<String, String> mapDayToGroup) {
        this.mapDayToGroup = mapDayToGroup;
    }

    public HashMap<String, String> getMapDayToGroup() {
        return mapDayToGroup;
    }
}
