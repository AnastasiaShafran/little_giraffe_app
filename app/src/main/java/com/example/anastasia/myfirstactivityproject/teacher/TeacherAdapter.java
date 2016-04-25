package com.example.anastasia.myfirstactivityproject.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;
import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by Anastasia on 4/24/2016.
 */
public class TeacherAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private TextView lblCbTchName, lblCbTchLastName, lblCbTchAge, lblCbTchPhone;
    private HashMap<String, Teacher> teacherHashMap;
    private Teacher teacher;
    private Firebase myFirebase;

    public TeacherAdapter(Context context, HashMap<String, Teacher> teacherHashMap) {
        this.context = context;
        this.teacherHashMap = teacherHashMap;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Firebase.setAndroidContext(context);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Teacher");
    }

    @Override
    public int getCount() {
        return teacherHashMap.size();
    }

    @Override
    public Object getItem(int position) {
        return  teacherHashMap.get(teacherHashMap.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View myView = convertView;


        if(myView == null){
            myView = layoutInflater.inflate(R.layout.activity_row_teachers, null);
        }
        //teacher = new Teacher();
        teacher = teacherHashMap.get(teacherHashMap.keySet().toArray()[position]);
        lblCbTchName = (TextView) myView.findViewById(R.id.lblFirstTeacherName);
        lblCbTchLastName = (TextView)myView.findViewById(R.id.lblLastTeacherName);
        lblCbTchAge = (TextView)myView.findViewById(R.id.lblTeacherAge);
        lblCbTchPhone = (TextView)myView.findViewById(R.id.lblTeacherPhone);
        lblCbTchName.setText(teacher.getName() + " ");
        lblCbTchLastName.setText(teacher.getLastName()+", ");
        lblCbTchAge.setText(teacher.getAge() + ",");
        lblCbTchPhone.setText(teacher.getPhone() + ".");


        return myView;
    }
}
