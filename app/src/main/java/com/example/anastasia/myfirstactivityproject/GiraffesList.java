package com.example.anastasia.myfirstactivityproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

public class GiraffesList extends AppCompatActivity {
    private MyAdapter myAdapter;
    private ListView lstGiraffesKids;
    private HashMap<String,Children> childrenMap;
    private Children myChildren;
    private Firebase myFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_giraffes);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Children");
        lstGiraffesKids = (ListView)findViewById(R.id.lstGiraffes);
        final String type = getIntent().getStringExtra("type");
        childrenMap = new HashMap<>();
        myAdapter = new MyAdapter(this,childrenMap);
        lstGiraffesKids.setAdapter(myAdapter);
        myFirebase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                myChildren = dataSnapshot.getValue(Children.class);
                String key = dataSnapshot.getKey();
                if (myChildren.getGroup().compareTo(type) == 0) {
                    childrenMap.put(key, myChildren);
                    myAdapter.notifyDataSetChanged();
                    lstGiraffesKids.invalidate();

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();
                childrenMap.remove(key);
                myAdapter.notifyDataSetChanged();
                lstGiraffesKids.invalidate();



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
