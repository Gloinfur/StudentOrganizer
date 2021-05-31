package com.jansmoneymachine.timetablestudents.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.jansmoneymachine.timetablestudents.R;

public class MainPopUpWork extends AppCompatActivity {

    private int width;
    private int heigth;
    private TextView txtView_mainPopupWorkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pop_up_work);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        heigth = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (heigth * 0.7));

        String info = "With the icon in the top right corner you can always add items (or activate the edit mode in 'Time Table') within the different tools."+
                "\n\nIf you click on the list items, you can delete or edit (not all) them."+
                "\n\nAt 'Time Table' you can create your custom time table by activating the edit mode in the top right corner. " +
                "\n\nAt 'Deadlines' you´re able to insert your deadlines for a quick overview. " +
                "\n\nTake simple notes at 'Notes' and share them with your colleagues. " +
                "\n\nWith the 'Grocery List' you will never forget anything at the supermarket. " +
                "\n\nAnd the last points will bring you to an very informative (german) YouTube channel for maths.";
        txtView_mainPopupWorkInfo = (TextView) findViewById(R.id.textView_popup_workInfo);
        txtView_mainPopupWorkInfo.setText(info);
    }
}
