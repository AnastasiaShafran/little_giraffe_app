package com.example.anastasia.myfirstactivityproject.ProfileTeacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anastasia.myfirstactivityproject.R;
import com.firebase.client.Firebase;

import java.util.HashMap;

public class AddTeacherActivity extends AppCompatActivity {

    private Firebase thFirebase;
    private Button btnCbAddTh;
    private EditText txtCbThName, txtCbThLastName, txtCbThAge, txtCbThPhone;
    private HashMap<String,Teacher> teacherMap = new HashMap<>();
    private Teacher teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_activity);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        thFirebase = refUrl.child("Teachers");
        onBtnSaveTeacherClick();

    }

    public void addTeacher(){
        teacher = new Teacher();
        txtCbThName = (EditText) findViewById(R.id.txtThName);
        txtCbThName.requestFocus();
        txtCbThLastName = (EditText) findViewById(R.id.txtThLastName);
        txtCbThAge = (EditText)findViewById(R.id.txtThAge);
        txtCbThPhone = (EditText)findViewById(R.id.txtThPhone);
        String name = txtCbThName.getText().toString();
        String lastName = txtCbThLastName.getText().toString();
        String age = txtCbThAge.getText().toString();
        String phone = txtCbThPhone.getText().toString();
        teacher.setName(name);
        teacher.setLastName(lastName);
        teacher.setAge(Integer.parseInt(age));
        teacher.setPhone(Integer.parseInt(phone));
        thFirebase.push().setValue(teacher);


    }
    public void onBtnSaveTeacherClick(){
        btnCbAddTh = (Button)findViewById(R.id.btbAddTeacher);
        btnCbAddTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();
                txtCbThName.setText("");
                txtCbThLastName.setText("");
                txtCbThAge.setText("");
                txtCbThPhone.setText("");
            }
        });
    }
}
