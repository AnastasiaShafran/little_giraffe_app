package com.example.anastasia.myfirstactivityproject.ParentsApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.child.ChildListForTiacherAdapter;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

public class ChildrenListForParents extends AppCompatActivity {

    private ListView lstCbKidForParent;
    private ParentAdapter parentAdapter;
    private Firebase myFirebase;


    private HashMap<String,Children> childrenMap;
    private Children myChildren;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_children_list_for_teacher);
            Firebase.setAndroidContext(this);
            Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
            myFirebase = refUrl.child("Children");
            lstCbKidForParent = (ListView)findViewById(R.id.lstChildrenForParents);
            childrenMap = new HashMap<>();
            parentAdapter = new ParentAdapter(this,childrenMap);
            lstCbKidForParent.setAdapter(parentAdapter);
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



        }
}
