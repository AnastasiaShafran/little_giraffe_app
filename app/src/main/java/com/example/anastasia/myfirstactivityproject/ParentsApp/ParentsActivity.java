package com.example.anastasia.myfirstactivityproject.ParentsApp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.KidActivityForDate;
import com.example.anastasia.myfirstactivityproject.pojo.KidGroupActivity;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;
import com.example.anastasia.myfirstactivityproject.weather.RestClient;
import com.example.anastasia.myfirstactivityproject.weather.WeatherResponse;
import com.example.anastasia.myfirstactivityproject.weather.WeatherRestInterface;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Context;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ParentsActivity extends AppCompatActivity {

    private Button btnCbChooseKid;
    private TextView tvKidName;
    private Firebase firebaseKid,firebaseTeach;
    private Query query;
    private String today;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_list);

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        today = sdf.format(new Date());

        CalendarView calendarView = (CalendarView)findViewById(R.id.cwParentApp);
        tvKidName = (TextView)findViewById(R.id.lblSelectedKid);
        btnCbChooseKid = (Button)findViewById(R.id.btnChooseKid);
        btnCbChooseKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toKidList = new Intent(ParentsActivity.this,ChildrenListForParents.class);
                startActivity(toKidList);
            }
        });



        String strName = (String) getIntent().getSerializableExtra("MyChildName");
        String strLastName = (String)getIntent().getSerializableExtra("MyChildLastName");

        if(strName !=null && strLastName != null){
            final String fullName = strName + " " + strLastName;
            Firebase.setAndroidContext(this);
            Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
            firebaseKid = refUrl.child("KidActivity");//.child(today);
            tvKidName.setText(fullName);
            firebaseKid.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    String keyDate = dataSnapshot.getKey();
                    if (keyDate.compareTo(today)==0){
                        KidGroupActivity kG = dataSnapshot.getValue(KidGroupActivity.class);
                        KidActivityForDate activities = kG.getActivityByChildName(fullName);
                        TextView tvArrive = (TextView)findViewById(R.id.lblYesArr);
                        TextView tvMorningSleep = (TextView)findViewById(R.id.lblYesMorn);
                        TextView tvLaunch = (TextView)findViewById(R.id.lblYesLaunch);
                        TextView tvAfternunSleep = (TextView)findViewById(R.id.lblYesAfter);
                        TextView tvBreakfast = (TextView)findViewById(R.id.lblYesBreak);
                        TextView tvFecal = (TextView)findViewById(R.id.lblYesFecal);
                        if(activities.isArrived()){
                            tvArrive.setText("Yes");
                        }else{
                            tvArrive.setText("Not Arrive");
                        }
                        if(activities.isBreakfast()){
                            tvBreakfast.setText("Yes");

                        }else{
                            tvBreakfast.setText("Not Eat");
                        }
                        if(activities.isMorningSleep()){
                            tvMorningSleep.setText("Yes");
                        }else{
                            tvMorningSleep.setText("Not Sleep");
                        }
                        if(activities.isLunch()){
                            tvLaunch.setText("Yes");
                        }else {
                            tvLaunch.setText("Not Eat");
                        }
                        if(activities.isAfternoonSleep()){
                            tvAfternunSleep.setText("Yes");
                        }else{
                            tvAfternunSleep.setText("Not Sleep");
                        }
                        if(activities.isFecal()){
                            tvFecal.setText("Yes");
                        }else{
                            tvFecal.setText("No Fecal");
                        }
                    }

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

        Button btnShowTeacherInfo = (Button)findViewById(R.id.btnTeacherInfoForParent);
        btnShowTeacherInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firebase.setAndroidContext(v.getContext());
                Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
                firebaseTeach = refUrl.child("Teachers");
                firebaseTeach.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        HashMap<String, Teacher> nameFhoneMap = new HashMap<String, Teacher>();
                        for(DataSnapshot dh : dataSnapshot.getChildren()){
                            Teacher t= dh.getValue(Teacher.class);
                            String key = dh.getKey();
                            String name = t.getName();
                            String phone = String.valueOf(t.getPhone());
                            nameFhoneMap.put(key,t);

                        }
                        ListView lst = (ListView)findViewById(R.id.lst);
                        AdapterForTeachListForParent a = new AdapterForTeachListForParent(ParentsActivity.this,nameFhoneMap);
                        lst.setAdapter(a);





                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }
        });







        getWeather();
    }


    private void getWeather(){
        final String API_KEY = "56d5221c96da3a2fc9dac01136beba97";
        WeatherRestInterface service = RestClient.getClient();
        final String city = "Ramat-Gan";
        Call<WeatherResponse> call = service.getCityWeather(city,API_KEY);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Response<WeatherResponse> response, Retrofit retrofit) {
                if(response.isSuccess())
                {
                    WeatherResponse result = response.body();

                    TextView weatherTemp =  (TextView)findViewById(R.id.lblTemp);
                    weatherTemp.setText(result.getTempraturCelsius());

                    TextView weatherDesc =  (TextView)findViewById(R.id.lblDesc);
                    weatherDesc.setText(result.getDescription());

                    TextView weatherCity =  (TextView)findViewById(R.id.lblWeather);
                    weatherCity.setText(city);

                    Picasso.with(ParentsActivity.this).load(result.getIconAddress()).into((ImageView) findViewById(R.id.imgIcon));




                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fail to get weather",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail to get weather",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
