package com.example.anastasia.myfirstactivityproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity implements OnClickListener{
   // private  Firebase myFirebase;
    private Button btnCbProfile,btnCbBabyList,btnCbToddlerList;


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
                myIntent.putExtra("type","toddler");
                startActivity(myIntent);
                break;
            default: break;

        }


    }
}
