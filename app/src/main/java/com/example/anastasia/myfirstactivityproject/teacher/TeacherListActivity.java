package com.example.anastasia.myfirstactivityproject.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

public class TeacherListActivity extends AppCompatActivity {
    private TeacherAdapter adapter;
    private ListView lstCbTeacher;
    private HashMap<String,Teacher> hashMapTeach;
    private Firebase firebaseTeach;
    private Teacher myTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        firebaseTeach = refUrl.child("Teacher");
        hashMapTeach = new HashMap<>();
        adapter = new TeacherAdapter(this,hashMapTeach);
        lstCbTeacher = (ListView)findViewById(R.id.lstTeacher);
        lstCbTeacher.setAdapter(adapter);

        firebaseTeach.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                myTeacher = dataSnapshot.getValue(Teacher.class);
                String key = dataSnapshot.getKey();
                hashMapTeach.put(key,myTeacher);
                adapter.notifyDataSetChanged();
                lstCbTeacher.invalidate();
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
