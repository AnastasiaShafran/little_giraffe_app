package com.example.anastasia.myfirstactivityproject.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.example.anastasia.myfirstactivityproject.pojo.KidActivityForDate;
import com.example.anastasia.myfirstactivityproject.pojo.KidGroupActivity;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ChildrenListForTeacher extends AppCompatActivity {

    private ChildListForTiacherAdapter childListForTiacherAdapter;
    private String today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_list_for_teacher);
        Firebase.setAndroidContext(this);

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        today = sdf.format(new Date());

        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");

        populateAdapterWithData(refUrl);





    }

    void populateAdapterWithData(final Firebase baseRef){
        baseRef.child("KidActivity").child(today).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    KidGroupActivity kidGroupActivity = snapshot.getValue(KidGroupActivity.class);
                    childListForTiacherAdapter = new ChildListForTiacherAdapter(ChildrenListForTeacher.this,kidGroupActivity,today);
                    ((ListView)findViewById(R.id.lstChildrenForTeacher)).setAdapter(childListForTiacherAdapter);

                }
                else {
                    baseRef.child("Children").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            KidGroupActivity kidGroupActivity = new KidGroupActivity();

                            for (DataSnapshot s : dataSnapshot.getChildren()){
                                Children ch = s.getValue(Children.class);
                                String name = ch.getFirstName() + " " + ch.getLastName();

                                kidGroupActivity.getKidsActivity().put(name,new KidActivityForDate());
                            }
                            childListForTiacherAdapter = new ChildListForTiacherAdapter(ChildrenListForTeacher.this,kidGroupActivity,today);
                            ((ListView)findViewById(R.id.lstChildrenForTeacher)).setAdapter(childListForTiacherAdapter);



                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });

    }
//


}
