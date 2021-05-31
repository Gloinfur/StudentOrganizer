package com.jansmoneymachine.timetablestudents.TimeTable.WeekOverview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jansmoneymachine.timetablestudents.R;
import java.util.Calendar;

public class WeekActivity_CustomAdapterListView extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private TextView txtView_dayName;
    private WeekActivityItemDatabase weekActivityItemDatabases;


    public WeekActivity_CustomAdapterListView(Context context, WeekActivityItemDatabase weekActivityItemDatabases) {
        this.context = context;
        this.weekActivityItemDatabases = weekActivityItemDatabases;
    }


    @Override
    public int getCount() {
        return weekActivityItemDatabases.getWeekActivityItems().length;
    }


    @Override
    public Object getItem(int position) {
        return weekActivityItemDatabases.getWeekActivityItems()[position];
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeekActivityItem[] weekActivityItems = weekActivityItemDatabases.getWeekActivityItems();
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_week_item, null);
        }

        txtView_dayName = (TextView) convertView.findViewById(R.id.txtView_dayName);

        if (weekActivityItems[position] != null) {
            txtView_dayName.setText(weekActivityItems[position].getNameDay());
           if (position == getCurrentDay()) {
              txtView_dayName.setTextColor(Color.rgb(10,101,197)); // = #0A65C5 = myBlue from colors.xml
           } else {
               txtView_dayName.setTextColor(Color.GRAY);
           }
        }
        return convertView;
    }


    public int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int day = 0;

        if (Calendar.MONDAY == dayOfWeek) {
            day = 0;
        } else if (Calendar.TUESDAY == dayOfWeek) {
            day = 1;
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            day = 2;
        } else if (Calendar.THURSDAY == dayOfWeek) {
            day = 3;
        } else if (Calendar.FRIDAY == dayOfWeek) {
            day = 4;
        } else if (Calendar.SATURDAY == dayOfWeek) {
            day = 5;
        } else if (Calendar.SUNDAY == dayOfWeek) {
            day = 700; // Need to return something else than 0 for SUNDAY, cause SUNDAY isn´t supported in the time table
        }
        System.out.println("day: " + dayOfWeek);
        return day;
    }
}


