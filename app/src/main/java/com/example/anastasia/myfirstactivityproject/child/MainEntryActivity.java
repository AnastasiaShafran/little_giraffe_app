package com.example.anastasia.myfirstactivityproject.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.EntryActivity.MainActivity;
import com.example.anastasia.myfirstactivityproject.ParentsApp.ParentsActivity;
import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.teacherapp.TeacherEntranceActivity;

public class MainEntryActivity extends AppCompatActivity implements OnClickListener{
   // private  Firebase myFirebase;
    private Button btnCbTeacherApp,btnCbParentApp,btnAdminApp;
    private TextView lblCbGroupTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry);

        btnCbTeacherApp = (Button)findViewById(R.id.btnTeacherApp);
        btnCbTeacherApp.setOnClickListener(this);
        btnCbParentApp = (Button)findViewById(R.id.btnParentApp);
        btnCbParentApp.setOnClickListener(this);
        btnAdminApp = (Button)findViewById(R.id.btnAdminApp);
        btnAdminApp.setOnClickListener(this);
        lblCbGroupTitle = (TextView)findViewById(R.id.lblTitleGroup);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnAdminApp:
                Intent intent1 = new Intent(MainEntryActivity.this, MainActivity.class);
                startActivity(intent1);
                break;

            case R.id.btnTeacherApp:
                Intent teachAppIntent = new Intent(MainEntryActivity.this, TeacherEntranceActivity.class);
                teachAppIntent.putExtra("typeB","baby");
                teachAppIntent.putExtra("typeT","toddler");
                startActivity(teachAppIntent);
                break;
            case R.id.btnParentApp:
                Intent toParentApp = new Intent(MainEntryActivity.this, ParentsActivity.class);
                startActivity(toParentApp);
                break;

            default: break;

        }


    }
}
