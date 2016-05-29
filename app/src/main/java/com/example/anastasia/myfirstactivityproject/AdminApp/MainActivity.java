package com.example.anastasia.myfirstactivityproject.AdminApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.child.ChildrenProfileActivity;
import com.example.anastasia.myfirstactivityproject.child.GiraffesList;
import com.example.anastasia.myfirstactivityproject.teacher.AddTeacherActivity;
import com.example.anastasia.myfirstactivityproject.teacher.TeacherListActivity;
import com.example.anastasia.myfirstactivityproject.teacher.WeeklySchedule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCbProfile,btnCbBabyList,btnCbToddlerList,btnCbTeacherProf,btnCbSchedule,btnCbTeacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCbBabyList = (Button) findViewById(R.id.btnBabyList);
        btnCbBabyList.setOnClickListener(this);
        btnCbProfile = (Button) findViewById(R.id.btnProfile);
        btnCbProfile.setOnClickListener(this);
        btnCbToddlerList = (Button) findViewById(R.id.btnToddlerList);
        btnCbToddlerList.setOnClickListener(this);
        btnCbTeacherProf = (Button)findViewById(R.id.btnTeacherProfile);
        btnCbTeacherProf.setOnClickListener(this);

        btnCbSchedule = (Button)findViewById(R.id.btnSchedule);
        btnCbSchedule.setOnClickListener(this);

        btnCbTeacherList = (Button)findViewById(R.id.btnTeacherList);
        btnCbTeacherList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnProfile:
                Intent intent = new Intent(MainActivity.this, ChildrenProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBabyList:

                Intent i = new Intent(MainActivity.this, GiraffesList.class);
                i.putExtra("type", "baby");
                startActivity(i);

                break;
            case R.id.btnToddlerList:
                Intent myIntent = new Intent(MainActivity.this, GiraffesList.class);
                myIntent.putExtra("type", "toddler");
                startActivity(myIntent);
                break;
            case R.id.btnTeacherProfile:
                Intent thIntent = new Intent(MainActivity.this, AddTeacherActivity.class);
                startActivity(thIntent);
                break;
            case R.id.btnSchedule:
                Intent schIntent = new Intent(MainActivity.this, WeeklySchedule.class);
                startActivity(schIntent);
                break;
            case R.id.btnTeacherList:
                Intent intentTch = new Intent(MainActivity.this,TeacherListActivity.class);
                startActivity(intentTch);
            default: break;

        }

    }
}
