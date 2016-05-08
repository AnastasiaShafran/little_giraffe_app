package com.example.anastasia.myfirstactivityproject.ParentsApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by Tal on 08/05/2016.
 */
public class ParentAdapter extends BaseAdapter {

    private Context context;
    private HashMap<String,Children> cMapForList;
    private LayoutInflater inflater;
    private Children c;
    private Firebase myFirebase;
    private TextView tvName, tvLastName;

    public ParentAdapter(Context context, HashMap<String, Children> childrenMapForList) {
        this.context = context;
        this.cMapForList = childrenMapForList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Firebase.setAndroidContext(context);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Children");
    }

    @Override
    public int getCount() {
        return cMapForList.size();
    }

    @Override
    public Object getItem(int position) {
        return cMapForList.get(cMapForList.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.activity_row_children_perents,null);
        }
        c = cMapForList.get(cMapForList.keySet().toArray()[position]);
        tvName = (TextView)view.findViewById(R.id.lblChildNameForParents);
        tvLastName = (TextView)view.findViewById(R.id.lblChildLastNameForParents);
        tvName.setText(c.getFirstName().toString());
        tvLastName.setText(c.getLastName().toString());

        return view;
    }
}
