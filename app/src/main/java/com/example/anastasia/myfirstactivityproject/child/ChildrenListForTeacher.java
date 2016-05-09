package com.example.anastasia.myfirstactivityproject.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Calendar;
import java.util.HashMap;

public class ChildrenListForTeacher extends AppCompatActivity {
    private Firebase myFirebase;
    private ListView lstCbListForTeacher;
    private ChildListForTiacherAdapter childListForTiacherAdapter;
    private TextView lblCbGroupTitle;
    private HashMap<String,Children> childrenMap;
    private Children myChildren;
    private StringBuilder date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children_list_for_teacher);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Children");
        lstCbListForTeacher = (ListView)findViewById(R.id.lstChildrenForTeacher);


        final String type = getIntent().getStringExtra("type");
        if(type.equals("baby")){
            lblCbGroupTitle = (TextView)findViewById(R.id.lblTitleGroup);
            lblCbGroupTitle.setText("Baby Giraffes");
        }
        if(type.equals("toddler")){
            lblCbGroupTitle = (TextView)findViewById(R.id.lblTitleGroup);
            lblCbGroupTitle.setText("Toddler Giraffes");

        }
        childrenMap = new HashMap<>();
        childListForTiacherAdapter = new ChildListForTiacherAdapter(this,childrenMap,date);
        lstCbListForTeacher.setAdapter(childListForTiacherAdapter);
        myFirebase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                myChildren = dataSnapshot.getValue(Children.class);
                String key = dataSnapshot.getKey();
                if (myChildren.getGroup().compareTo(type) == 0) {
                    childrenMap.put(key, myChildren);
                   childListForTiacherAdapter.notifyDataSetChanged();
                    lstCbListForTeacher.invalidate();

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                myChildren = dataSnapshot.getValue(Children.class);
                String key = dataSnapshot.getKey();
                if (myChildren.getGroup().compareTo(type) == 0) {
                    childrenMap.put(key, myChildren);
                    childListForTiacherAdapter.notifyDataSetChanged();
                    lstCbListForTeacher.invalidate();


                }



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();
                childrenMap.remove(key);
                childListForTiacherAdapter.notifyDataSetChanged();
                lstCbListForTeacher.invalidate();



            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





    }
//


}
