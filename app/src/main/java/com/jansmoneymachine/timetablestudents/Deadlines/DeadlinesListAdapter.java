package com.jansmoneymachine.timetablestudents.Deadlines;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jansmoneymachine.timetablestudents.R;
import java.util.ArrayList;
import java.util.List;

public class DeadlinesListAdapter extends BaseAdapter {

    private static final String tag = "DeadlinesListAdapter";
    private ArrayList<DeadlineItem> deadlineItemArrayList;
    private Context context;

    public DeadlinesListAdapter(Context context, ArrayList<DeadlineItem> arrayList) {
        this.context = context;
        this.deadlineItemArrayList = arrayList;
    }


    @Override
    public int getCount() {
        return deadlineItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return deadlineItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.activity_deadlines_item, null);

        TextView txtView_description = (TextView) convertView.findViewById(R.id.txtView_deadlinesItemDescription);
        TextView txtView_date = (TextView) convertView.findViewById(R.id.txtView_deadlinesItemDate);
        TextView txtView_time = (TextView) convertView.findViewById(R.id.txtView_deadlinesItemTime);
        TextView txtView_reminder = (TextView) convertView.findViewById(R.id.txtView_deadlinesItemReminder);

        DeadlineItem deadlineItem = deadlineItemArrayList.get(position);

        txtView_description.setText(deadlineItem.getDeadlineDescription());
        txtView_date.setText(deadlineItem.getDeadlineDateFinal());
        txtView_time.setText(deadlineItem.getDeadlineTimeFinal());
        txtView_reminder.setText(deadlineItem.getDeadlineReminder());

        return convertView;
    }


    public List<DeadlineItem> getData() {
        return deadlineItemArrayList;
    }
}
