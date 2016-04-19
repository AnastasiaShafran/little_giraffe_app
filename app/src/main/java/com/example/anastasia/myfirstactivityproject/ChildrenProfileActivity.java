package com.example.anastasia.myfirstactivityproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.HashMap;

public class ChildrenProfileActivity extends AppCompatActivity {

    private Button btnCbUpdate;
    private EditText txtCbChildName, txtCbChildLastName, txtCbBirthDay,txtCbPhone,txtCbMomName, txtCbDadName, txtCbMomEmail, txtCbDadEmail, txtCbGroup;
    private Firebase firebase;
    private HashMap<String,Children> myChildrenMap = new HashMap<String,Children>();
    private Children myChildren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_profile);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        firebase = refUrl.child("Children");
        onBtnClickUpdate();


    }
    public void saveChild(){
        txtCbChildName = (EditText) findViewById(R.id.txtChildName);
        txtCbChildLastName = (EditText) findViewById(R.id.txtChildLastName);
        txtCbBirthDay = (EditText) findViewById(R.id.txtBirthDay);
        txtCbPhone = (EditText)findViewById(R.id.txtPhone);
        txtCbMomName = (EditText) findViewById(R.id.txtMomName);
        txtCbDadName = (EditText) findViewById(R.id.txtDadName);
        txtCbMomEmail = (EditText) findViewById(R.id.txtMomEmail);
        txtCbDadEmail = (EditText) findViewById(R.id.txtDadEmail);
        txtCbGroup = (EditText)findViewById(R.id.txtGroup);
        myChildren = new Children();
        String childName = txtCbChildName.getText().toString();
        String childLastName = txtCbChildLastName.getText().toString();
        String birthDay = txtCbBirthDay.getText().toString();
        String phone = txtCbPhone.getText().toString();
        String momName = txtCbMomName.getText().toString();
        String dadName = txtCbDadName.getText().toString();
        String momEmail = txtCbMomEmail.getText().toString();
        String dadEmail = txtCbDadEmail.getText().toString();
        String group = txtCbGroup.getText().toString();

        myChildren.setFirstName(childName);
        myChildren.setLastName(childLastName);
        myChildren.setDateOfBirth(birthDay);
        myChildren.setPhone(phone);
        myChildren.setMomName(momName);
        myChildren.setDadName(dadName);
        myChildren.setEmailMom(momEmail);
        myChildren.setEmailDad(dadEmail);
        myChildren.setGroup(group);
        firebase.push().setValue(myChildren);
    }
    public void onBtnClickUpdate(){
        btnCbUpdate = (Button)findViewById(R.id.btnUpdateChildProfile);
        btnCbUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChild();
                txtCbChildName.setText("");
                txtCbChildName.requestFocus();
                txtCbChildLastName.setText("");
                txtCbBirthDay.setText("");
                txtCbPhone.setText("");
                txtCbMomName.setText("");
                txtCbDadName.setText("");
                txtCbMomEmail.setText("");
                txtCbDadEmail.setText("");
                txtCbGroup.setText("");


            }
        });
    }
}
