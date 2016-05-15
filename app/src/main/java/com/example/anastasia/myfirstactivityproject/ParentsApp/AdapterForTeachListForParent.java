package com.example.anastasia.myfirstactivityproject.ParentsApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;

import java.util.HashMap;

/**
 * Created by Tal on 15/05/2016.
 */
public class AdapterForTeachListForParent extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private HashMap<String,Teacher> hm;
    private Teacher t;


    public AdapterForTeachListForParent(Context context, HashMap<String, Teacher> hm) {
        this.context = context;
        this.hm = hm;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return hm.size();
    }

    @Override
    public Object getItem(int position) {

        return hm.get(hm.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if(myView == null){
            myView = inflater.inflate(R.layout.activity_list,null);
        }



       t = (Teacher) hm.get(hm.keySet().toArray()[position]);
        TextView tvName = (TextView)myView.findViewById(R.id.lblTeach1NameForParentApp);
        TextView tvPhone = (TextView)myView.findViewById(R.id.lblPhone1);
        tvName.setText(t.getName());
        tvPhone.setText(String.valueOf(t.getPhone()));
        return myView;
    }
}
