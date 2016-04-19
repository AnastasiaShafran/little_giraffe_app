package com.example.anastasia.myfirstactivityproject;

import android.app.Activity;
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

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Anastasia on 4/17/2016.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private HashMap<String,Children> childrenMap;
    private LayoutInflater inflater;
    private  Children c;
    private Firebase myFirebase;


    public MyAdapter(Context context, HashMap<String, Children> childrenMap) {
        this.context = context;
        this.childrenMap = childrenMap;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Firebase.setAndroidContext(context);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Children");
    }




    @Override
    public int getCount() {
        return childrenMap.size();
    }

    @Override
    public Object getItem(int position) {
        return childrenMap.get(childrenMap.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        View myView = convertView;
        if(myView==null){

            myView = inflater.inflate(R.layout.activity_row_children,null);
        }
        c = childrenMap.get(childrenMap.keySet().toArray()[position]);
        TextView lblChildName = (TextView)myView.findViewById(R.id.lblFirstName);
        TextView lblChildLastName = (TextView)myView.findViewById(R.id.lblLastName);
        TextView lblBirthDay = (TextView)myView.findViewById(R.id.lblDateOfBirth);
        TextView lblPhoneNum = (TextView)myView.findViewById(R.id.lblPhone);
        TextView lblType = (TextView)myView.findViewById(R.id.lblGroup);
        Button btnCbInfo = (Button)myView.findViewById(R.id.btnMoreInfo);
        Button btnCbUpdate = (Button)myView.findViewById(R.id.btnUpdate);
        Button btnCbRemove = (Button)myView.findViewById(R.id.btnRemove);
        lblChildName.setText(c.getFirstName().toString()+" ");
        lblChildLastName.setText(c.getLastName().toString()+ ", ");
        lblBirthDay.setText(c.getDateOfBirth().toString()+ ", ");
        lblPhoneNum.setText(c.getPhone().toString()+ ", ");
        lblType.setText(c.getGroup().toString() + ".");

        btnCbInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = childrenMap.get(childrenMap.keySet().toArray()[position]);
                Intent intent = new Intent(context, MoreInfoActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("Child", c);
                intent.putExtras(b);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });




        btnCbRemove.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context).setMessage("Do you wont to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String key = (String) childrenMap.keySet().toArray()[position];
                                myFirebase.child(key).removeValue();
                                Toast.makeText(context, "Child Removed", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();




            }

        });

        btnCbUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return myView;
    }
}
