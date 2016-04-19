package com.example.anastasia.myfirstactivityproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class MoreInfoActivity extends AppCompatActivity implements Serializable {
    Children children;
    TextView lblCbMomMoreInfoName,lblCbMoreInfoDadName,lblCbMomEmail,lblCbDadEmail;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Intent i = getIntent();
        children=(Children)getIntent().getSerializableExtra("Child");
//        key = (String)getIntent().getSerializableExtra("Val");
        lblCbMomMoreInfoName = (TextView)findViewById(R.id.lblMomFirstNameMoreInfo);
        lblCbMoreInfoDadName = (TextView)findViewById(R.id.lblDadFirstNameMoreInfo);
        lblCbMomEmail = (TextView)findViewById(R.id.lblMomEmailMoreInfo);
        lblCbDadEmail = (TextView)findViewById(R.id.lblDadEmailMoreInfo);
        lblCbMomMoreInfoName.setText(children.getMomName().toString());
        lblCbMoreInfoDadName.setText(children.getDadName());
        lblCbMomEmail.setText(children.getEmailMom());
        lblCbDadEmail.setText(children.getEmailDad());



    }
}
