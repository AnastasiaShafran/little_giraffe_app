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
import com.example.anastasia.myfirstactivityproject.pojo.KidActivityForDate;
import com.example.anastasia.myfirstactivityproject.pojo.KidGroupActivity;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParentsActivity extends AppCompatActivity {

    private Button btnCbChooseKid;
    private TextView tvKidName;
    private Firebase firebaseKid;
    private Query query;
    private String today;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        today = sdf.format(new Date());

        CalendarView calendarView = (CalendarView)findViewById(R.id.cwParentApp);
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

        if(strName !=null && strLastName != null){
            final String fullName = strName + " " + strLastName;
            Firebase.setAndroidContext(this);
            Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
            firebaseKid = refUrl.child("KidActivity");//.child(today);
            tvKidName.setText(fullName);
            firebaseKid.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    String keyDate = dataSnapshot.getKey();
                    if (keyDate.compareTo(today)==0){
                        KidGroupActivity kG = dataSnapshot.getValue(KidGroupActivity.class);
                        KidActivityForDate activities = kG.getActivityByChildName(fullName);


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






    }
}
