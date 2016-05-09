package com.example.anastasia.myfirstactivityproject.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;
import com.firebase.client.Firebase;

import java.util.HashMap;

public class AddTeacherActivity extends AppCompatActivity {

    private Firebase thFirebase;
    private Button btnCbAddTh,btnCbUpdateTeacher;
    private EditText txtCbThName, txtCbThLastName, txtCbThAge, txtCbThPhone;
    private HashMap<String,Teacher> teacherMap = new HashMap<>();
    private Teacher teacher;
    String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_activity);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        thFirebase = refUrl.child("Teachers");
        txtCbThName = (EditText) findViewById(R.id.txtThName);
        txtCbThName.requestFocus();
        txtCbThLastName = (EditText) findViewById(R.id.txtThLastName);
        txtCbThAge = (EditText)findViewById(R.id.txtThAge);
        txtCbThPhone = (EditText)findViewById(R.id.txtThPhone);
        teacher = new Teacher();
        str = (String) getIntent().getSerializableExtra("teacherKey");
        Teacher t = (Teacher)getIntent().getSerializableExtra("teacherValue");
        if(str == null || t == null){
            onBtnSaveTeacherClick();
        }else{
            update(t);
            onBtnClickUpdate();


        }




    }
    public void update(Teacher teacherToUpdate){

        txtCbThName.setText(teacherToUpdate.getName());
        txtCbThLastName.setText(teacherToUpdate.getLastName());
        txtCbThAge.setText(String.valueOf(teacherToUpdate.getAge()));
        txtCbThPhone.setText(String.valueOf(teacherToUpdate.getPhone()));

    }
    public void onBtnClickUpdate(){
        txtCbThName = (EditText) findViewById(R.id.txtThName);
        txtCbThName.requestFocus();
        txtCbThLastName = (EditText) findViewById(R.id.txtThLastName);
        txtCbThAge = (EditText)findViewById(R.id.txtThAge);
        txtCbThPhone = (EditText)findViewById(R.id.txtThPhone);
        btnCbUpdateTeacher = (Button)findViewById(R.id.btbAddTeacher);
        btnCbUpdateTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtCbThName.getText().toString();
                String lastName = txtCbThLastName.getText().toString();
                String age = txtCbThAge.getText().toString();
                String phone = txtCbThPhone.getText().toString();
                Teacher newTeacher = new Teacher();
                newTeacher.setName(name);
                newTeacher.setLastName(lastName);
                newTeacher.setAge(Integer.parseInt(age));
                newTeacher.setPhone(Integer.parseInt(phone));
                thFirebase.child(str).setValue(newTeacher);

                cleanControl();
            }
        });


    }

    public void addTeacher(){
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


    public void cleanControl(){
        txtCbThName.setText("");
        txtCbThLastName.setText("");
        txtCbThAge.setText("");
        txtCbThPhone.setText("");

    }
}
