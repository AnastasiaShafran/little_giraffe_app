package com.example.anastasia.myfirstactivityproject.pojo;

import android.widget.EditText;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Anastasia on 4/24/2016.
 */
public class WorkScedule implements Serializable {
    String startDate;
    HashMap<String,TeacherSchedule>  schedule;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {

        this.startDate = startDate;
    }

    public HashMap<String, TeacherSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(HashMap<String, TeacherSchedule> schedule) {
        this.schedule = schedule;
    }

    public WorkScedule() {
        schedule = new HashMap<>();
        startDate = new String();

    }

    public void addTeacherWorkWeek(String name, TeacherSchedule sc){
        schedule.put(name,sc);
    }


}
