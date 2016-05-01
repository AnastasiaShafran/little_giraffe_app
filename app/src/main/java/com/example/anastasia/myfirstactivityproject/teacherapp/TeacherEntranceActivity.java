package com.example.anastasia.myfirstactivityproject.teacherapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.child.GiraffesList;

import java.util.Calendar;

public class TeacherEntranceActivity extends AppCompatActivity {
    private Spinner spinner;
    private String[] group = {"baby", "toddler"};
    private Button btnEntry,btnCbSeeSchedule;
    private TextView lblCbDisplayDate;
    private Calendar calendar;
    private int day,month,year;
    static final int DATE_DIALOG_ID = 999;


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


        setCurrentDateOnView();
        onBtnClickChangeDate();



    }
    public void setCurrentDateOnView() {

        lblCbDisplayDate = (TextView) findViewById(R.id.lblDate);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        lblCbDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("-").append(month + 1).append("-")
                .append(year).append(" "));



    }
    public  void onBtnClickChangeDate(){
        btnCbSeeSchedule = (Button)findViewById(R.id.btnSeeYourSchedule);
        btnCbSeeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:

                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;


           lblCbDisplayDate.setText(new StringBuilder().append(day)
                    .append("-").append(month + 1).append("-").append(year)
                    .append(" "));



        }
    };

}

