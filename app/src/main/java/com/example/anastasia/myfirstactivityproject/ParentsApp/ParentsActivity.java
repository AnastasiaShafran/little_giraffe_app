package com.example.anastasia.myfirstactivityproject.ParentsApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;

public class ParentsActivity extends AppCompatActivity {
    private String[] group = {"baby", "toddler"};
    private Spinner spinner;
    private Button btnCbChooseKid;
    private TextView tvKidName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);
        CalendarView calendarView = (CalendarView)findViewById(R.id.cwParentApp);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        tvKidName = (TextView)findViewById(R.id.lblSelectedKid);
        btnCbChooseKid = (Button)findViewById(R.id.btnChooseKid);
        btnCbChooseKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toKidList = new Intent(ParentsActivity.this,ChildrenListForParents.class);
                startActivity(toKidList);
            }
        });

        String strName = (String) getIntent().getSerializableExtra("MyChildName");
        String strLastName = (String)getIntent().getSerializableExtra("MyChildLastName");
        tvKidName.setText(strName + " " + strLastName);


    }
}
