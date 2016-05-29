package com.example.anastasia.myfirstactivityproject.ParentsApp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.child.ChildListForTiacherAdapter;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.example.anastasia.myfirstactivityproject.pojo.KidActivityForDate;
import com.example.anastasia.myfirstactivityproject.pojo.KidGroupActivity;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChildrenListForParents extends AppCompatActivity {

    private ListView lstCbKidForParent;
    private ParentAdapter parentAdapter;
    private Firebase myFirebase;
    final Context ctx = this;
    private Firebase firebaseKid;
    private Query query;
    private String today;
    private HashMap<String,Children> childrenMap;
    private Children myChildren;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_children_list_for_parents);
            Firebase.setAndroidContext(this);
            Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
            myFirebase = refUrl.child("Children");

            lstCbKidForParent = (ListView)findViewById(R.id.lstChildrenForParents);
            childrenMap = new HashMap<>();
            parentAdapter = new ParentAdapter(this,childrenMap);
            lstCbKidForParent.setAdapter(parentAdapter);
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            today = sdf.format(new Date());



            myFirebase.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    myChildren = dataSnapshot.getValue(Children.class);
                    String key = dataSnapshot.getKey();
                        childrenMap.put(key, myChildren);
                        parentAdapter.notifyDataSetChanged();
                        lstCbKidForParent.invalidate();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    myChildren = dataSnapshot.getValue(Children.class);
                    String key = dataSnapshot.getKey();

                        childrenMap.put(key, myChildren);
                        parentAdapter.notifyDataSetChanged();
                        lstCbKidForParent.invalidate();



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
            lstCbKidForParent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      Children c = (Children) parentAdapter.getItem(position);
                      String name = c.getFirstName().toString();
                      String lastName = c.getLastName().toString();

                    Intent toActivityParents = new Intent(ChildrenListForParents.this,ParentsActivity.class);
                    toActivityParents.putExtra("MyChildName", (Serializable) name);
                    toActivityParents.putExtra("MyChildLastName", (Serializable) lastName);
                    startActivity(toActivityParents);

                }
            });



        }
//    public void getData(){
//        firebaseKid.child("KidActivity").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String date = dataSnapshot.getKey();
//                KidGroupActivity kidGroupActivity = dataSnapshot.getValue(KidGroupActivity.class);
//                kidGroupActivity.getKidsActivity();
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//    }


    }

