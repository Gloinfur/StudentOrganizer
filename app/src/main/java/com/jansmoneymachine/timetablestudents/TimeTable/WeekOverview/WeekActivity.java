package com.jansmoneymachine.timetablestudents.TimeTable.WeekOverview;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jansmoneymachine.timetablestudents.R;
import com.jansmoneymachine.timetablestudents.TimeTable.DayOverview.FridayActivity;
import com.jansmoneymachine.timetablestudents.TimeTable.DayOverview.MondayActivity;
import com.jansmoneymachine.timetablestudents.TimeTable.DayOverview.SaturdayActivity;
import com.jansmoneymachine.timetablestudents.TimeTable.DayOverview.ThursdayActivity;
import com.jansmoneymachine.timetablestudents.TimeTable.DayOverview.TuesdayActivity;
import com.jansmoneymachine.timetablestudents.TimeTable.DayOverview.WednesdayActivity;

import java.util.Calendar;

public class WeekActivity extends AppCompatActivity {
    private Toolbar toolbar_Week;
    private ListView listView_Week;
    private WeekActivityItemDatabase weekActivityItemDatabase = new WeekActivityItemDatabase();
    private WeekActivity_CustomAdapterListView customAdapterListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        setUpView();
    }


    private void setUpView() {
        // Initializing toolbar
        toolbar_Week = (Toolbar) findViewById(R.id.toolbar_Week);
        setSupportActionBar(toolbar_Week);
        getSupportActionBar().setTitle("Time Table");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // ListView
        listView_Week = (ListView) findViewById(R.id.listView_Week);
        customAdapterListView = new WeekActivity_CustomAdapterListView(this, weekActivityItemDatabase);
        listView_Week.setAdapter(customAdapterListView);
        listView_Week.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    // Monday
                    case 0:
                        Intent intent0 = new Intent (WeekActivity.this, MondayActivity.class);
                        startActivity(intent0);
                        break;

                    // Tuesday
                    case 1:
                        Intent intent1 = new Intent (WeekActivity.this, TuesdayActivity.class);
                        startActivity(intent1);
                        break;

                    // Wednesday
                    case 2:
                        Intent intent2 = new Intent (WeekActivity.this, WednesdayActivity.class);
                        startActivity(intent2);
                        break;

                    // Thursday
                    case 3:
                        Intent intent3 = new Intent (WeekActivity.this, ThursdayActivity.class);
                        startActivity(intent3);
                        break;

                    // Friday
                    case 4:
                        Intent intent4 = new Intent (WeekActivity.this, FridayActivity.class);
                        startActivity(intent4);
                        break;

                    // Saturday
                    case 5:
                        Intent intent5 = new Intent (WeekActivity.this, SaturdayActivity.class);
                        startActivity(intent5);
                        break;

                    // Default
                    default:
                        break;
                }
            }
        });
    }


    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

