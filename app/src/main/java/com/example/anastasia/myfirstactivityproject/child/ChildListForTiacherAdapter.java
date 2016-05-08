package com.example.anastasia.myfirstactivityproject.child;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by Anastasia on 5/3/2016.
 */
public class ChildListForTiacherAdapter extends BaseAdapter {

    private Context context;
    private HashMap<String,Children> childrenMapForList;
    private LayoutInflater inflater;
    private Children c;
    private Firebase myFirebase;
    private CheckBox checkBoxArrive, checkBoxNotArrive, checkBoxBreakfast;
    Button btnCbUpdateActivity;


    public ChildListForTiacherAdapter(Context context, HashMap<String, Children> childrenMap) {

        this.childrenMapForList = childrenMap;
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Firebase.setAndroidContext(context);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Children");
    }

    @Override
    public int getCount() {
        return childrenMapForList.size();
    }

    @Override
    public Object getItem(int position) {
        return childrenMapForList.get(childrenMapForList.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if (myView == null) {

            myView = inflater.inflate(R.layout.activity_row_childrenlist_fortiacher, null);

        }
        c = childrenMapForList.get(childrenMapForList.keySet().toArray()[position]);
        TextView lblChildName = (TextView) myView.findViewById(R.id.lblChildNameForTeachList);
        TextView lblChildLastName = (TextView) myView.findViewById(R.id.lblChildLastNameForTeachList);
        lblChildName.setText(c.getFirstName().toString());
        lblChildLastName.setText(c.getLastName().toString());
        checkBoxArrive = (CheckBox)myView.findViewById(R.id.cbArrive);
        checkBoxNotArrive = (CheckBox)myView.findViewById(R.id.cbNotArravi);
        btnCbUpdateActivity = (Button)myView.findViewById(R.id.btnUpdateActivity);
        btnCbUpdateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxArrive.isChecked()){
                    Toast.makeText(context,"Arraive",Toast.LENGTH_LONG).show();
                }
                if(checkBoxNotArrive.isChecked()){
                    Toast.makeText(context,"Not Arrive",Toast.LENGTH_LONG).show();
                }
            }
        });


        return myView;
    }
}
