package com.example.anastasia.myfirstactivityproject.teacherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.child.GiraffesList;

public class TeacherEntranceActivity extends AppCompatActivity {
    private Spinner spinner;
    private String[] group = {"baby", "toddler"};
    private Button btnEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_entrance);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        btnEntry = (Button) findViewById(R.id.btnEntryTeacher);
        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedGroup = (String) spinner.getSelectedItem();
                Intent i = new Intent(TeacherEntranceActivity.this, GiraffesList.class);
                i.putExtra("type", selectedGroup);
                startActivity(i);


            }
        });

    }
}
