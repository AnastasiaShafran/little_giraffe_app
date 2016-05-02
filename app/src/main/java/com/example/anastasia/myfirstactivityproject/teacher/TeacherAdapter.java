package com.example.anastasia.myfirstactivityproject.teacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.child.ChildrenProfileActivity;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
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
    private Button btnCbMoreInfoTeacher, btnCbTeacherUpdate,btnCbTeacherRemove;
    private HashMap<String, Teacher> teacherHashMap;
    private Teacher teacher;
    private Firebase myFirebase;

    public TeacherAdapter(Context context, HashMap<String, Teacher> teacherHashMap) {
        this.context = context;
        this.teacherHashMap = teacherHashMap;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Firebase.setAndroidContext(context);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Teachers");
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
    public View getView(final int position, View convertView, ViewGroup parent) {

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
        btnCbMoreInfoTeacher = (Button)myView.findViewById(R.id.btnMoreTeacherInfo);
        btnCbTeacherUpdate = (Button)myView.findViewById(R.id.btnTeacherUpdate);
        btnCbTeacherRemove = (Button)myView.findViewById(R.id.btnTeacherRemove);
        lblCbTchName.setText(teacher.getName().toString() + " ");
        lblCbTchLastName.setText(teacher.getLastName().toString()+", ");
        lblCbTchAge.setText(String.valueOf(teacher.getAge()) + ",");
        lblCbTchPhone.setText(String.valueOf(teacher.getPhone()) + ".");

        btnCbTeacherUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = (String) teacherHashMap.keySet().toArray()[position];
                teacher = (Teacher) getItem(position);
                Intent myIntent = new Intent(context,AddTeacherActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("teacherKey", key);
                b.putSerializable("teacherValue", teacher);
                myIntent.putExtras(b);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);

            }
        });
        btnCbTeacherRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context).setMessage("Are you shore you wont to remove Teacher from the list");
                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String key = (String) teacherHashMap.keySet().toArray()[position];
                        myFirebase.child(key).removeValue();
                        Toast.makeText(context, "Teacher Removed", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        });

        return myView;
    }

}
