package com.example.anastasia.myfirstactivityproject.child;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.anastasia.myfirstactivityproject.pojo.KidActivityForDate;
import com.example.anastasia.myfirstactivityproject.pojo.KidGroupActivity;
import com.firebase.client.Firebase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anastasia on 5/3/2016.
 */
public class ChildListForTiacherAdapter extends BaseAdapter {

    private Context context;
//    private HashMap<String,Children> childrenMapForList;
//    private HashMap<String,KidGroupActivity> hashMap;
    private LayoutInflater inflater;
    //private Children c;

    private Firebase myFirebase;
    private Firebase firebase;
    private Button btnCbUpdateActivity;
    private String today;

    private KidGroupActivity kidGroupActivity;
    //private KidActivityForDate kidActivityForDate;



    public ChildListForTiacherAdapter(Context context, KidGroupActivity kidActivity,String date) {

        this.kidGroupActivity= kidActivity;
        this.context = context;
        this.today = date;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Firebase.setAndroidContext(context);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");

        firebase = refUrl.child("KidActivity");


    }

    @Override
    public int getCount() {
        return kidGroupActivity.getKidsActivity().size();
    }

    @Override
    public Object getItem(int position) {
        Map<String,KidActivityForDate> childrenMapForList = kidGroupActivity.getKidsActivity();
        return childrenMapForList.get(childrenMapForList.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View myView = convertView;

        if (myView == null) {

            myView = inflater.inflate(R.layout.activity_row_childrenlist_fortiacher, null);

        }
        Map<String,KidActivityForDate> childrenMapForList = kidGroupActivity.getKidsActivity();
        String c = (String) childrenMapForList.keySet().toArray()[position];
        KidActivityForDate activities =childrenMapForList.get(c);

        TextView lblChildName = (TextView) myView.findViewById(R.id.lblChildNameForTeachList);
        TextView  lblCbDateOftoDay = (TextView)myView.findViewById(R.id.lblDateOftoDay);
        lblCbDateOftoDay.setText(today);
        lblChildName.setText(c);


        ((CheckBox)myView.findViewById(R.id.cbArrive)).setChecked(activities.isArrived());
        ((CheckBox)myView.findViewById(R.id.cbBreakfest)).setChecked(activities.isBreakfast());
        ((CheckBox)myView.findViewById(R.id.cbMorningSleep)).setChecked(activities.isMorningSleep());
        ((CheckBox)myView.findViewById(R.id.cbLanch)).setChecked(activities.isLunch());
        ((CheckBox)myView.findViewById(R.id.cbAfternoonSleep)).setChecked(activities.isAfternoonSleep());
        ((CheckBox)myView.findViewById(R.id.cbFecal)).setChecked(activities.isFecal());

        btnCbUpdateActivity = (Button)myView.findViewById(R.id.btnUpdateActivity);
        final View listView = myView;

        btnCbUpdateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,KidActivityForDate> childrenMapForList = kidGroupActivity.getKidsActivity();
                String c = (String) childrenMapForList.keySet().toArray()[position];
                KidActivityForDate activities =childrenMapForList.get(c);

                activities.setArrived( ((CheckBox)listView.findViewById(R.id.cbArrive)).isChecked());
                activities.setBreakfast( ((CheckBox)listView.findViewById(R.id.cbBreakfest)).isChecked());
                activities.setMorningSleep( ((CheckBox)listView.findViewById(R.id.cbMorningSleep)).isChecked());
                activities.setLunch( ((CheckBox)listView.findViewById(R.id.cbLanch)).isChecked());
                activities.setAfternoonSleep( ((CheckBox)listView.findViewById(R.id.cbAfternoonSleep)).isChecked());
                activities.setFecal( ((CheckBox)listView.findViewById(R.id.cbFecal)).isChecked());
                kidGroupActivity.getKidsActivity().put(c,activities);



                firebase.child(today).setValue(kidGroupActivity);





            }
        });




        return myView;
    }
}
