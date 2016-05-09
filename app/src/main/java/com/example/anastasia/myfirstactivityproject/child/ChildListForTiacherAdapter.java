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
import com.example.anastasia.myfirstactivityproject.pojo.KidActivityForDate;
import com.example.anastasia.myfirstactivityproject.pojo.KidGroupActivity;
import com.firebase.client.Firebase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Anastasia on 5/3/2016.
 */
public class ChildListForTiacherAdapter extends BaseAdapter {

    private Context context;
    private HashMap<String,Children> childrenMapForList;
    private HashMap<String,KidGroupActivity> hashMap;
    private LayoutInflater inflater;
    private Children c;
    private Firebase myFirebase;
    private Firebase firebase;
    private Button btnCbUpdateActivity;
    private StringBuilder date;
    private KidGroupActivity kidGroupActivity;
    private KidActivityForDate kidActivityForDate;



    public ChildListForTiacherAdapter(Context context, HashMap<String, Children> childrenMap,StringBuilder myDate) {

        this.childrenMapForList = childrenMap;
        this.context = context;
        this.date = myDate;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Firebase.setAndroidContext(context);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Children");
        firebase = refUrl.child("KidActivity");


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int  month = calendar.get(Calendar.MONTH );
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date = new StringBuilder();
        date.append(day).append(month+1).append(year);
        //String strDate = date.toString();



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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if (myView == null) {

            myView = inflater.inflate(R.layout.activity_row_childrenlist_fortiacher, null);

        }
        c = childrenMapForList.get(childrenMapForList.keySet().toArray()[position]);
        TextView lblChildName = (TextView) myView.findViewById(R.id.lblChildNameForTeachList);
        TextView lblChildLastName = (TextView) myView.findViewById(R.id.lblChildLastNameForTeachList);
        TextView  lblCbDateOftoDay = (TextView)myView.findViewById(R.id.lblDateOftoDay);
        lblChildName.setText(c.getFirstName().toString());
        lblChildLastName.setText(c.getLastName().toString());
        lblCbDateOftoDay.setText(date);

        btnCbUpdateActivity = (Button)myView.findViewById(R.id.btnUpdateActivity);
        final View listView = myView;

        btnCbUpdateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = childrenMapForList.get(childrenMapForList.keySet().toArray()[position]);

                CheckBox checkBoxArrive = (CheckBox)listView.findViewById(R.id.cbArrive);
                CheckBox checkBoxBreakfast = (CheckBox)listView.findViewById(R.id.cbBreakfest);

                if(checkBoxArrive.isChecked() || checkBoxBreakfast.isChecked() ){
                    Toast.makeText(context,"Arrive",Toast.LENGTH_LONG).show();
                    String name = c.getFirstName();
                    KidActivityForDate kidActivityForDate = new KidActivityForDate();
                    kidActivityForDate.setArrived(true);
                    kidActivityForDate.setBreakfast(true);
                    KidGroupActivity kidGroupActivity = new KidGroupActivity();

                    kidGroupActivity.getKidsActivity().put(name,kidActivityForDate);

                    hashMap = new HashMap<String, KidGroupActivity>();
                    hashMap.put(date.toString(),kidGroupActivity);
                    firebase.setValue(hashMap);

                }


            }
        });




        return myView;
    }
}
