package com.jansmoneymachine.timetablestudents.Deadlines;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.jansmoneymachine.timetablestudents.R;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class DeadlinesAddNewDeadlineItem extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private EditText editTxt_deadlineDescription;
    private Button btn_dueDate;
    private Button btn_dueTime;
    private EditText editTxt_reminderHour;
    private Toolbar toolbar_DeadlinesAddNewItem;

    // For setting the notification
    private int yearN;
    private int monthN;
    private int dayN;
    private int hourN;
    private int minuteN;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private int yearFinal;
    private String monthFinal;
    private String dayFinal;
    private String hourFinal;
    private String minuteFinal;
    private String dateFinal;
    private String timeFinal;

    private DeadlinesDbHelper deadlinesDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines_add_new_deadline_item);

        setupView();
        pickDate();
        pickTime();
    }


    private void setupView() {
        toolbar_DeadlinesAddNewItem = (Toolbar) findViewById(R.id.toolbar_DeadlinesAddNewItem);
        setSupportActionBar(toolbar_DeadlinesAddNewItem);
        getSupportActionBar().setTitle("New deadline");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTxt_deadlineDescription = (EditText) findViewById(R.id.editTxt_newDeadlineDescription);
        btn_dueDate = (Button) findViewById(R.id.btn_newDeadlineDate);
        btn_dueTime = (Button) findViewById(R.id.btn_newDeadlineTime);
        editTxt_reminderHour = (EditText) findViewById(R.id.editTxt_newDeadlineHourReminder);

        deadlinesDbHelper = new DeadlinesDbHelper(this);
    }


    private void pickDate() {
        btn_dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(DeadlinesAddNewDeadlineItem.this, R.style.MyDatePickerDialogStyle, DeadlinesAddNewDeadlineItem.this, year, month, day);
                datePickerDialog.show();
            }
        });
    }


    private void pickTime() {
        btn_dueTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(DeadlinesAddNewDeadlineItem.this, R.style.MyTimePickerDialogStyle, DeadlinesAddNewDeadlineItem.this, hour, minute, DateFormat.is24HourFormat(DeadlinesAddNewDeadlineItem.this));
                timePickerDialog.show();
            }
        });
    }


    public void setNotification() {
        int reminderHour = Integer.parseInt(editTxt_reminderHour.getText().toString());
        final int requestCode = (int) System.currentTimeMillis(); //RequestCode has to be unique to not cancel previous alarm

        if (dateFinal != null && timeFinal != null) {
            Calendar calendar = Calendar.getInstance();


            calendar.set(Calendar.YEAR, yearN);
            calendar.set(Calendar.MONTH, monthN);
            // If dayN - 1 == 0;
            if (dayN < 1) {
                calendar.set(Calendar.MONTH, monthN - 1);
                Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, dayN);
            }
            calendar.set(Calendar.HOUR_OF_DAY, hourN - reminderHour);
            calendar.set(Calendar.MINUTE, minuteN);
            calendar.set(Calendar.SECOND, 0);

            /* for testing
            System.out.println(hourN);
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' hh:mm");
            System.out.println(format.format(calendar.getTime()));
            System.out.println("RequestCode: "+requestCode);
            */

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, DeadlinesAlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent,0);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            if (reminderHour == 0) {
                Toast.makeText(DeadlinesAddNewDeadlineItem.this, "No reminder before!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DeadlinesAddNewDeadlineItem.this, "Will remind you " + reminderHour + "h before!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // For setting the notification
        yearN = year;
        monthN = month;
        dayN = dayOfMonth;

        yearFinal = year;
        monthFinal = String.valueOf(month + 1);
        dayFinal = String.valueOf(dayOfMonth);

        if (monthFinal.length() == 1) {
            monthFinal = "0" + monthFinal;
        }
        if (dayFinal.length() == 1) {
            dayFinal = "0" + dayFinal;
        }
        dateFinal = dayFinal + "." + monthFinal + "." + yearFinal;
        btn_dueDate.setText(dateFinal);
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // For setting the notification
        hourN = hourOfDay;
        minuteN = minute;

        hourFinal = String.valueOf(hourOfDay);
        minuteFinal = String.valueOf(minute);

        if (minuteFinal.length() == 1) {
            minuteFinal = "0" + minuteFinal;
        }
        if (hourFinal.length() == 1) {
            hourFinal = "0" + hourFinal;
        }

        timeFinal = hourFinal + ":" + minuteFinal;
        btn_dueTime.setText(timeFinal);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.deadlines_save_item:
                // Catches missing input with error message & also prevents the app from crashing
                if ((TextUtils.isEmpty(editTxt_reminderHour.getText())) && TextUtils.isEmpty(editTxt_deadlineDescription.getText())) {
                    editTxt_deadlineDescription.setError("Input missing!");
                    editTxt_reminderHour.setError("Input missing!");
                } else if (TextUtils.isEmpty(editTxt_reminderHour.getText())) {
                    editTxt_reminderHour.setError("Input missing!");
                } else if ((TextUtils.isEmpty(editTxt_deadlineDescription.getText()))) {
                    editTxt_deadlineDescription.setError("Input missing!");
                } else {
                    deadlinesDbHelper.insertNewDeadline(editTxt_deadlineDescription.getText().toString(), dateFinal, timeFinal, "Reminder: " + editTxt_reminderHour.getText().toString() + "h before");
                    setNotification();
                    Intent intent = new Intent(DeadlinesAddNewDeadlineItem.this, Deadlines.class);
                    startActivity(intent);
                }
        }
        return super.onOptionsItemSelected(item);
    }


    // Solution -- MENU Symbol didn't exist
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deadlines_add_new_item, menu);
        return true;
    }
}
