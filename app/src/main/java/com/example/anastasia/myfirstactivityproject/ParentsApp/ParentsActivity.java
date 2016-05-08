package com.example.anastasia.myfirstactivityproject.ParentsApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.example.anastasia.myfirstactivityproject.R;

public class ParentsActivity extends AppCompatActivity {
    private String[] group = {"baby", "toddler"};
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);
        CalendarView calendarView = (CalendarView)findViewById(R.id.cwParentApp);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);




    }
}
