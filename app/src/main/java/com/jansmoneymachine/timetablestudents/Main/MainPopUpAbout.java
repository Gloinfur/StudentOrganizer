package com.jansmoneymachine.timetablestudents.Main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.jansmoneymachine.timetablestudents.R;

public class MainPopUpAbout extends AppCompatActivity {
    private int width;
    private int heigth;
    private TextView txtView_mainPopupAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pop_up_about);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        heigth = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (heigth * 0.7));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        String info = "The 'Student Organzier' is a individual project for the course 'Mobile Application Development' at the 'Hochschule Ulm' in Germany." +
                "\n\nIn this course every student had to create an app, which has to be useful for the students at this university of applied science." +
                "\n\nThe 'Student Organizer' is a simple tool, which gathers all important features for a structured study life.";
        txtView_mainPopupAbout = (TextView) findViewById(R.id.textView_popup_aboutInfo);
        txtView_mainPopupAbout.setText(info);
    }
}
