package com.example.anastasia.myfirstactivityproject.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.ParentsApp.ParentsActivity;
import com.example.anastasia.myfirstactivityproject.teacher.AddTeacherActivity;
import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.teacher.TeacherListActivity;
import com.example.anastasia.myfirstactivityproject.teacher.WeeklySchedule;
import com.example.anastasia.myfirstactivityproject.teacherapp.TeacherEntranceActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener{
   // private  Firebase myFirebase;
    private Button btnCbProfile,btnCbBabyList,btnCbToddlerList,btnCbTeacherProf,btnCbSchedule,btnCbTeacherApp,btnCbParentApp,btnCbTeacherList;
    private TextView lblCbGroupTitle;


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
        lblCbGroupTitle = (TextView)findViewById(R.id.lblTitleGroup);
        btnCbSchedule = (Button)findViewById(R.id.btnSchedule);
        btnCbSchedule.setOnClickListener(this);
        btnCbTeacherApp = (Button)findViewById(R.id.btnTeacherApp);
        btnCbTeacherApp.setOnClickListener(this);
        btnCbParentApp = (Button)findViewById(R.id.btnParentsApp);
        btnCbParentApp.setOnClickListener(this);
        btnCbTeacherList = (Button)findViewById(R.id.btnTeacherList);
        btnCbTeacherList.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.btnProfile:
                Intent intent = new Intent(MainActivity.this, ChildrenProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBabyList:

                Intent i = new Intent(MainActivity.this,GiraffesList.class);
                i.putExtra("type","baby");
                startActivity(i);

                break;
            case R.id.btnToddlerList:
                Intent myIntent = new Intent(MainActivity.this,GiraffesList.class);
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
            case R.id.btnTeacherApp:
                Intent teachAppIntent = new Intent(MainActivity.this, TeacherEntranceActivity.class);
                teachAppIntent.putExtra("typeB","baby");
                teachAppIntent.putExtra("typeT","toddler");
                startActivity(teachAppIntent);
                break;
            case R.id.btnParentsApp:
                Intent toParentApp = new Intent(MainActivity.this, ParentsActivity.class);
                startActivity(toParentApp);
                break;
            case R.id.btnTeacherList:
                Intent intentTch = new Intent(MainActivity.this,TeacherListActivity.class);
                startActivity(intentTch);


            default: break;

        }


    }
}
