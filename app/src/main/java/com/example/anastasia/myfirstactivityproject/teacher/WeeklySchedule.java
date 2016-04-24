package com.example.anastasia.myfirstactivityproject.teacher;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;
import com.example.anastasia.myfirstactivityproject.pojo.TeacherSchedule;
import com.example.anastasia.myfirstactivityproject.pojo.WorkScedule;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

public class WeeklySchedule extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private TableLayout table;
    private Firebase myFirebase;
    private Firebase firebaseSchedule;
    private final HashMap<String,Teacher> teachMap = new HashMap<>();;
    private Teacher teacher = new Teacher();
    private Spinner sunday,monday,tuesday,wednesday,thursday, friday;
//    int color_blue = -16776961;
//    int color_gray = -7829368;
//    int color_black = -16777216;
    int color_white = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_schedule);
        table = (TableLayout) findViewById(R.id.tableLayout1);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Teachers");
        firebaseSchedule = refUrl.child("Schedule");
        setOnBtnClickSave();



        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //  dataSnapshot.getChildrenCount();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    teacher = d.getValue(Teacher.class);
                    String key = d.getKey();
                    teachMap.put(key, teacher);


                }

                createTableRows(WeeklySchedule.this);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
    public void createTableRows(Context ctx)
    {
        TableRow header = new TableRow(ctx);
        header.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        header.setBackgroundColor(Color.GRAY);
        TextView teachName = new TextView(ctx);
        TextView sun = new TextView(ctx);
        TextView mon = new TextView(ctx);
        TextView tues = new TextView(ctx);
        TextView wed = new TextView(ctx);
        TextView thurs = new TextView(ctx);
        TextView fri = new TextView(ctx);
        teachName.setText("Name");
        teachName.setTextSize(16);
        teachName.setTypeface(Typeface.DEFAULT_BOLD);
        teachName.setWidth(150);
        teachName.setTextColor(Color.BLUE);
        sun.setText("Sun");
        sun.setTextSize(16);
        sun.setTypeface(Typeface.DEFAULT_BOLD);
        sun.setWidth(150);
        sun.setTextColor(Color.YELLOW);
        mon.setText("Mon");
        mon.setTextSize(16);
        mon.setTypeface(Typeface.DEFAULT_BOLD);
        mon.setWidth(150);
        mon.setTextColor(Color.GREEN);
        tues.setText("Tues");
        tues.setTextSize(16);
        tues.setTypeface(Typeface.DEFAULT_BOLD);
        tues.setWidth(150);
        tues.setTextColor(Color.RED);
        wed.setText("Wed");
        wed.setTextSize(16);
        wed.setTypeface(Typeface.DEFAULT_BOLD);
        wed.setWidth(150);
        wed.setTextColor(Color.CYAN);
        thurs.setText("Thurs");
        thurs.setTextSize(16);
        thurs.setTypeface(Typeface.DEFAULT_BOLD);
        thurs.setWidth(150);
        thurs.setTextColor(Color.MAGENTA);
        fri.setText("Fri");
        fri.setTextSize(16);
        fri.setTypeface(Typeface.DEFAULT_BOLD);
        fri.setWidth(150);
        fri.setTextColor(Color.BLACK);
        header.addView(teachName);
        header.addView(sun);
        header.addView(mon);
        header.addView(tues);
        header.addView(wed);
        header.addView(thurs);
        header.addView(fri);
        table.addView(header);


        for (int i = 0; i < teachMap.size(); i++)
        {
            TableRow table_row = new TableRow(ctx);
            TextView tv_name = new TextView(ctx);
            sunday = new Spinner(ctx);
            monday = new Spinner(ctx);
            tuesday = new Spinner(ctx);
            wednesday = new Spinner(ctx);
            thursday = new Spinner(ctx);
            friday = new Spinner(ctx);
            String[]group = {"baby", "toddler", "ooo"};
            ArrayAdapter adapter = new ArrayAdapter(ctx,android.R.layout.simple_spinner_item, group );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sunday.setAdapter(adapter);
            monday.setAdapter(adapter);
            tuesday.setAdapter(adapter);
            wednesday.setAdapter(adapter);
            thursday.setAdapter(adapter);
            friday.setAdapter(adapter);

            ImageView img_line = new ImageView(ctx);

            table_row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
            table_row.setBackgroundColor(color_white);
            teacher = teachMap.get(teachMap.keySet().toArray()[i]);
            tv_name.setText(teacher.getName().toString());
            tv_name.setTextSize(16);
            tv_name.setTypeface(Typeface.DEFAULT_BOLD);
            tv_name.setWidth(150);
            tv_name.setTextColor(Color.BLACK);
            tv_name.setBackgroundColor(Color.WHITE);

            img_line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
            img_line.setBackgroundResource(R.drawable.table_row_bg);

            table_row.addView(tv_name);
            table_row.addView(sunday);
            table_row.addView(monday);
            table_row.addView(tuesday);
            table_row.addView(wednesday);
            table_row.addView(thursday);
            table_row.addView(friday);

            table.addView(table_row);
            table.addView(img_line);

        }
    }


    public void setOnBtnClickSave(){
        Button btnCbSave = (Button)findViewById(R.id.btnSave);
        btnCbSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveSchedule();

            }
        });
    }

    public void saveSchedule(){

        WorkScedule scedule = new WorkScedule();

        for (int i=1; i< table.getChildCount(); i++){
            View v = table.getChildAt(i);
            if (v instanceof TableRow) {
                TableRow row = (TableRow )v;
                String name = (String)((TextView)row.getChildAt(0)).getText();
                String sunday = (String)((Spinner)row.getChildAt(1)).getSelectedItem();
                String monday= (String)((Spinner)row.getChildAt(2)).getSelectedItem();
                String tuesday= (String)((Spinner)row.getChildAt(3)).getSelectedItem();
                String wend= (String)((Spinner)row.getChildAt(4)).getSelectedItem();
                String thursday = (String)((Spinner)row.getChildAt(5)).getSelectedItem();
                String friday =  (String)((Spinner)row.getChildAt(6)).getSelectedItem();

                TeacherSchedule teacherSchedule = new TeacherSchedule();
                teacherSchedule.getMapDayToGroup().put("Sun",sunday);
                teacherSchedule.getMapDayToGroup().put("Mon",monday);
                teacherSchedule.getMapDayToGroup().put("Tues",tuesday);
                teacherSchedule.getMapDayToGroup().put("Wed",wend);
                teacherSchedule.getMapDayToGroup().put("Thurs",thursday);
                teacherSchedule.getMapDayToGroup().put("Fri",friday);

                scedule.addTeacherWorkWeek(name,teacherSchedule);
            }

        }


        firebaseSchedule.child(scedule.getStartDate()).setValue(scedule);






    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
