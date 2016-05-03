package com.example.anastasia.myfirstactivityproject.teacherapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.child.GiraffesList;
import com.example.anastasia.myfirstactivityproject.pojo.TeacherSchedule;
import com.example.anastasia.myfirstactivityproject.pojo.WorkScedule;
import com.example.anastasia.myfirstactivityproject.teacher.ReadOnlyTeacherScheduleActivity;
import com.example.anastasia.myfirstactivityproject.teacher.WeeklySchedule;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.Calendar;
import java.util.HashMap;

public class TeacherEntranceActivity extends AppCompatActivity {
    private Spinner spinner;
    private String[] group = {"baby", "toddler"};
    private Firebase mySceduleRef;
    private  Query queryRef;
    private Button btnEntry,btnCbPickDate,btnCbShowSchedule;
    private HashMap<String, HashMap<String, TeacherSchedule>> hashMap;
    private TextView lblCbDisplayDate;
    private Calendar calendar;
    private int day,month,year;

    static final int DATE_DIALOG_ID = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_entrance);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        mySceduleRef = refUrl.child("Schedule");
        //queryRef = mySceduleRef.orderByChild("startDate");
        showWeeklySchedule();
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
        btnCbPickDate = (Button)findViewById(R.id.btnPickDate);
        btnCbPickDate.setOnClickListener(new View.OnClickListener() {
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

    public void showWeeklySchedule(){

        btnCbShowSchedule = (Button)findViewById(R.id.btnShowSchedule);
        final Context ctx = this;

        btnCbShowSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateForScedule = lblCbDisplayDate.getText().toString();
                queryRef = mySceduleRef.orderByKey().startAt(dateForScedule).endAt(dateForScedule);

                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        WorkScedule w = dataSnapshot.getValue(WorkScedule.class);
                        String str = dataSnapshot.getKey();

                        if(str.compareTo(lblCbDisplayDate.getText().toString()) == 0){

                            Toast.makeText(getApplicationContext(),"This Date is fained",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TeacherEntranceActivity.this, ReadOnlyTeacherScheduleActivity.class);
                            Bundle b = new Bundle();
                            b.putSerializable("schedule", w);
                            intent.putExtras(b);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ctx.startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"This Date not Exist",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });
    }



}

