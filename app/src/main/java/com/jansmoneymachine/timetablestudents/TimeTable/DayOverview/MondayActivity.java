package com.jansmoneymachine.timetablestudents.TimeTable.DayOverview;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.jansmoneymachine.timetablestudents.R;

public class MondayActivity extends AppCompatActivity {
    private Toolbar toolbar_Day;

    private EditText editTxt_subjectName0;
    private EditText editTxt_subjectRoom0;
    private EditText editTxt_subjectProf0;

    private EditText editTxt_subjectName1;
    private EditText editTxt_subjectRoom1;
    private EditText editTxt_subjectProf1;

    private EditText editTxt_subjectName2;
    private EditText editTxt_subjectRoom2;
    private EditText editTxt_subjectProf2;

    private EditText editTxt_subjectName3;
    private EditText editTxt_subjectRoom3;
    private EditText editTxt_subjectProf3;

    private EditText editTxt_subjectName4;
    private EditText editTxt_subjectRoom4;
    private EditText editTxt_subjectProf4;

    private EditText editTxt_subjectName5;
    private EditText editTxt_subjectRoom5;
    private EditText editTxt_subjectProf5;

    private EditText editTxt_subjectName6;
    private EditText editTxt_subjectRoom6;
    private EditText editTxt_subjectProf6;

    private boolean editMode;

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        sharedPrefs = this.getSharedPreferences("Monday", MODE_PRIVATE);

        setUpView();
        setUpElements();
        setEditMode();
    }


    private void setUpElements() {
        editTxt_subjectName0 = findViewById(R.id.editTxt_subjectName0);
        editTxt_subjectRoom0 = findViewById(R.id.editTxt_subjectRoom0);
        editTxt_subjectProf0 = findViewById(R.id.editTxt_subjectProf0);
        editTxt_subjectName0.setText(sharedPrefs.getString("subjectName0",null));
        editTxt_subjectRoom0.setText(sharedPrefs.getString("subjectRoom0",null));
        editTxt_subjectProf0.setText(sharedPrefs.getString("subjectProf0",null));

        editTxt_subjectName1 = findViewById(R.id.editTxt_subjectName1);
        editTxt_subjectRoom1 = findViewById(R.id.editTxt_subjectRoom1);
        editTxt_subjectProf1 = findViewById(R.id.editTxt_subjectProf1);
        editTxt_subjectName1.setText(sharedPrefs.getString("subjectName1",null));
        editTxt_subjectRoom1.setText(sharedPrefs.getString("subjectRoom1",null));
        editTxt_subjectProf1.setText(sharedPrefs.getString("subjectProf1",null));

        editTxt_subjectName2 = findViewById(R.id.editTxt_subjectName2);
        editTxt_subjectRoom2 = findViewById(R.id.editTxt_subjectRoom2);
        editTxt_subjectProf2 = findViewById(R.id.editTxt_subjectProf2);
        editTxt_subjectName2.setText(sharedPrefs.getString("subjectName2",null));
        editTxt_subjectRoom2.setText(sharedPrefs.getString("subjectRoom2",null));
        editTxt_subjectProf2.setText(sharedPrefs.getString("subjectProf2",null));

        editTxt_subjectName3 = findViewById(R.id.editTxt_subjectName3);
        editTxt_subjectRoom3 = findViewById(R.id.editTxt_subjectRoom3);
        editTxt_subjectProf3 = findViewById(R.id.editTxt_subjectProf3);
        editTxt_subjectName3.setText(sharedPrefs.getString("subjectName3",null));
        editTxt_subjectRoom3.setText(sharedPrefs.getString("subjectRoom3",null));
        editTxt_subjectProf3.setText(sharedPrefs.getString("subjectProf3",null));

        editTxt_subjectName4 = findViewById(R.id.editTxt_subjectName4);
        editTxt_subjectRoom4 = findViewById(R.id.editTxt_subjectRoom4);
        editTxt_subjectProf4 = findViewById(R.id.editTxt_subjectProf4);
        editTxt_subjectName4.setText(sharedPrefs.getString("subjectName4",null));
        editTxt_subjectRoom4.setText(sharedPrefs.getString("subjectRoom4",null));
        editTxt_subjectProf4.setText(sharedPrefs.getString("subjectProf4",null));

        editTxt_subjectName5 = findViewById(R.id.editTxt_subjectName5);
        editTxt_subjectRoom5 = findViewById(R.id.editTxt_subjectRoom5);
        editTxt_subjectProf5 = findViewById(R.id.editTxt_subjectProf5);
        editTxt_subjectName5.setText(sharedPrefs.getString("subjectName5",null));
        editTxt_subjectRoom5.setText(sharedPrefs.getString("subjectRoom5",null));
        editTxt_subjectProf5.setText(sharedPrefs.getString("subjectProf5",null));

        editTxt_subjectName6 = findViewById(R.id.editTxt_subjectName6);
        editTxt_subjectRoom6 = findViewById(R.id.editTxt_subjectRoom6);
        editTxt_subjectProf6 = findViewById(R.id.editTxt_subjectProf6);
        editTxt_subjectName6.setText(sharedPrefs.getString("subjectName6",null));
        editTxt_subjectRoom6.setText(sharedPrefs.getString("subjectRoom6",null));
        editTxt_subjectProf6.setText(sharedPrefs.getString("subjectProf6",null));

        editMode = false;
    }


    private void setUpView() {
        // Initializing toolbar
        toolbar_Day = (Toolbar) findViewById(R.id.toolbar_Day);
        setSupportActionBar(toolbar_Day);
        getSupportActionBar().setTitle("Time Table Monday");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void setEditMode() {
        if (editMode == false) {
            editMode = true;

            editTxt_subjectName0.setFocusable(false);
            editTxt_subjectName0.setInputType(InputType.TYPE_NULL);
            editTxt_subjectRoom0.setFocusable(false);
            editTxt_subjectRoom0.setInputType(InputType.TYPE_NULL);
            editTxt_subjectProf0.setFocusable(false);
            editTxt_subjectProf0.setInputType(InputType.TYPE_NULL);

            editTxt_subjectName1.setFocusable(false);
            editTxt_subjectName1.setInputType(InputType.TYPE_NULL);
            editTxt_subjectRoom1.setFocusable(false);
            editTxt_subjectRoom1.setInputType(InputType.TYPE_NULL);
            editTxt_subjectProf1.setFocusable(false);
            editTxt_subjectProf1.setInputType(InputType.TYPE_NULL);

            editTxt_subjectName2.setFocusable(false);
            editTxt_subjectName2.setInputType(InputType.TYPE_NULL);
            editTxt_subjectRoom2.setFocusable(false);
            editTxt_subjectRoom2.setInputType(InputType.TYPE_NULL);
            editTxt_subjectProf2.setFocusable(false);
            editTxt_subjectProf2.setInputType(InputType.TYPE_NULL);

            editTxt_subjectName3.setFocusable(false);
            editTxt_subjectName3.setInputType(InputType.TYPE_NULL);
            editTxt_subjectRoom3.setFocusable(false);
            editTxt_subjectRoom3.setInputType(InputType.TYPE_NULL);
            editTxt_subjectProf3.setFocusable(false);
            editTxt_subjectProf3.setInputType(InputType.TYPE_NULL);

            editTxt_subjectName4.setFocusable(false);
            editTxt_subjectName4.setInputType(InputType.TYPE_NULL);
            editTxt_subjectRoom4.setFocusable(false);
            editTxt_subjectRoom4.setInputType(InputType.TYPE_NULL);
            editTxt_subjectProf4.setFocusable(false);
            editTxt_subjectProf4.setInputType(InputType.TYPE_NULL);

            editTxt_subjectName5.setFocusable(false);
            editTxt_subjectName5.setInputType(InputType.TYPE_NULL);
            editTxt_subjectRoom5.setFocusable(false);
            editTxt_subjectRoom5.setInputType(InputType.TYPE_NULL);
            editTxt_subjectProf5.setFocusable(false);
            editTxt_subjectProf5.setInputType(InputType.TYPE_NULL);

            editTxt_subjectName6.setFocusable(false);
            editTxt_subjectName6.setInputType(InputType.TYPE_NULL);
            editTxt_subjectRoom6.setFocusable(false);
            editTxt_subjectRoom6.setInputType(InputType.TYPE_NULL);
            editTxt_subjectProf6.setFocusable(false);
            editTxt_subjectProf6.setInputType(InputType.TYPE_NULL);

        } else {

            editMode = false;

            editTxt_subjectName0.setFocusableInTouchMode(true);
            editTxt_subjectName0.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectRoom0.setFocusableInTouchMode(true);
            editTxt_subjectRoom0.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectProf0.setFocusableInTouchMode(true);
            editTxt_subjectProf0.setInputType(InputType.TYPE_CLASS_TEXT);

            editTxt_subjectName1.setFocusableInTouchMode(true);
            editTxt_subjectName1.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectRoom1.setFocusableInTouchMode(true);
            editTxt_subjectRoom1.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectProf1.setFocusableInTouchMode(true);
            editTxt_subjectProf1.setInputType(InputType.TYPE_CLASS_TEXT);

            editTxt_subjectName2.setFocusableInTouchMode(true);
            editTxt_subjectName2.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectRoom2.setFocusableInTouchMode(true);
            editTxt_subjectRoom2.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectProf2.setFocusableInTouchMode(true);
            editTxt_subjectProf2.setInputType(InputType.TYPE_CLASS_TEXT);

            editTxt_subjectName3.setFocusableInTouchMode(true);
            editTxt_subjectName3.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectRoom3.setFocusableInTouchMode(true);
            editTxt_subjectRoom3.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectProf3.setFocusableInTouchMode(true);
            editTxt_subjectProf3.setInputType(InputType.TYPE_CLASS_TEXT);

            editTxt_subjectName4.setFocusableInTouchMode(true);
            editTxt_subjectName4.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectRoom4.setFocusableInTouchMode(true);
            editTxt_subjectRoom4.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectProf4.setFocusableInTouchMode(true);
            editTxt_subjectProf4.setInputType(InputType.TYPE_CLASS_TEXT);

            editTxt_subjectName5.setFocusableInTouchMode(true);
            editTxt_subjectName5.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectRoom5.setFocusableInTouchMode(true);
            editTxt_subjectRoom5.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectProf5.setFocusableInTouchMode(true);
            editTxt_subjectProf5.setInputType(InputType.TYPE_CLASS_TEXT);

            editTxt_subjectName6.setFocusableInTouchMode(true);
            editTxt_subjectName6.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectRoom6.setFocusableInTouchMode(true);
            editTxt_subjectRoom6.setInputType(InputType.TYPE_CLASS_TEXT);
            editTxt_subjectProf6.setFocusableInTouchMode(true);
            editTxt_subjectProf6.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }


    // Solution -- MENU Symbol didn't exist
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_day, menu);
        return true;
    }


    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.item_edit_mode:
                setEditMode();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
        sharedPrefsEditor = this.getSharedPreferences("Monday", MODE_PRIVATE).edit();

        sharedPrefsEditor.putString("subjectName0", String.valueOf(editTxt_subjectName0.getText()));
        sharedPrefsEditor.putString("subjectRoom0", String.valueOf(editTxt_subjectRoom0.getText()));
        sharedPrefsEditor.putString("subjectProf0", String.valueOf(editTxt_subjectProf0.getText()));

        sharedPrefsEditor.putString("subjectName1", String.valueOf(editTxt_subjectName1.getText()));
        sharedPrefsEditor.putString("subjectRoom1", String.valueOf(editTxt_subjectRoom1.getText()));
        sharedPrefsEditor.putString("subjectProf1", String.valueOf(editTxt_subjectProf1.getText()));

        sharedPrefsEditor.putString("subjectName2", String.valueOf(editTxt_subjectName2.getText()));
        sharedPrefsEditor.putString("subjectRoom2", String.valueOf(editTxt_subjectRoom2.getText()));
        sharedPrefsEditor.putString("subjectProf2", String.valueOf(editTxt_subjectProf2.getText()));

        sharedPrefsEditor.putString("subjectName3", String.valueOf(editTxt_subjectName3.getText()));
        sharedPrefsEditor.putString("subjectRoom3", String.valueOf(editTxt_subjectRoom3.getText()));
        sharedPrefsEditor.putString("subjectProf3", String.valueOf(editTxt_subjectProf3.getText()));

        sharedPrefsEditor.putString("subjectName4", String.valueOf(editTxt_subjectName4.getText()));
        sharedPrefsEditor.putString("subjectRoom4", String.valueOf(editTxt_subjectRoom4.getText()));
        sharedPrefsEditor.putString("subjectProf4", String.valueOf(editTxt_subjectProf4.getText()));

        sharedPrefsEditor.putString("subjectName5", String.valueOf(editTxt_subjectName5.getText()));
        sharedPrefsEditor.putString("subjectRoom5", String.valueOf(editTxt_subjectRoom5.getText()));
        sharedPrefsEditor.putString("subjectProf5", String.valueOf(editTxt_subjectProf5.getText()));

        sharedPrefsEditor.putString("subjectName6", String.valueOf(editTxt_subjectName6.getText()));
        sharedPrefsEditor.putString("subjectRoom6", String.valueOf(editTxt_subjectRoom6.getText()));
        sharedPrefsEditor.putString("subjectProf6", String.valueOf(editTxt_subjectProf6.getText()));
        sharedPrefsEditor.commit();
    }
}
