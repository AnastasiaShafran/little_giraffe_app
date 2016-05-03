package com.example.anastasia.myfirstactivityproject.teacher;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;
import com.example.anastasia.myfirstactivityproject.pojo.TeacherSchedule;
import com.example.anastasia.myfirstactivityproject.pojo.WorkScedule;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class ReadOnlyTeacherScheduleActivity extends AppCompatActivity {
    private TableLayout table;
   // private final HashMap<String,Teacher> teachMap = new HashMap<>();;
    private TextView sunday,monday,tuesday,wednesday,thursday, friday;
    private WorkScedule w;
    private String date;
    int color_white = -1;
    private Firebase myFirebase;
    private Firebase firebaseSchedule;
    private Teacher teacher = new Teacher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_schedule);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        myFirebase = refUrl.child("Teachers");
        firebaseSchedule = refUrl.child("Schedule");
        table = (TableLayout)findViewById(R.id.tableLayout2);

        w = (WorkScedule) getIntent().getSerializableExtra("schedule");
        date = (String)getIntent().getSerializableExtra("date");
       createTableRows(this,w);



    }

    public void createTableRows(Context ctx, WorkScedule ws)
    {
        TableRow header = new TableRow(ctx);
        header.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        header.setBackgroundColor(Color.GRAY);
        TextView myDate = new TextView(ctx);
        TextView teachName = new TextView(ctx);
        TextView sun = new TextView(ctx);
        TextView mon = new TextView(ctx);
        TextView tues = new TextView(ctx);
        TextView wed = new TextView(ctx);
        TextView thurs = new TextView(ctx);
        TextView fri = new TextView(ctx);
        myDate.setText(date);
        myDate.setTextSize(16);
        myDate.setTypeface(Typeface.DEFAULT_BOLD);
        myDate.setWidth(150);
        myDate.setTextColor(Color.BLUE);
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


        for (Map.Entry<String,TeacherSchedule> entry : ws.getSchedule().entrySet())
        {

            String key = entry.getKey();
            TeacherSchedule t = entry.getValue();
            TableRow table_row = new TableRow(ctx);
            TextView tv_name = new TextView(ctx);
            tv_name.setText(key);
            HashMap<String,String>hm = t.getMapDayToGroup();
            String groupSun = hm.get(hm.keySet().toArray()[0]);
            String groupMun = hm.get(hm.keySet().toArray()[1]);
            String groupTue = hm.get(hm.keySet().toArray()[2]);
            String groupWed = hm.get(hm.keySet().toArray()[3]);
            String groupThur = hm.get(hm.keySet().toArray()[4]);
            String groupFri = hm.get(hm.keySet().toArray()[5]);


            sunday = new TextView(ctx);
            sunday.setText(groupSun);
            monday = new TextView(ctx);
            monday.setText(groupMun);
            tuesday = new TextView(ctx);
            tuesday.setText(groupTue);
            wednesday = new TextView(ctx);
            wednesday.setText(groupWed);
            thursday = new TextView(ctx);
            thursday.setText(groupThur);
            friday = new TextView(ctx);
            friday.setText(groupFri);

            ImageView img_line = new ImageView(ctx);

            table_row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT));
            table_row.setBackgroundColor(color_white);

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
}
