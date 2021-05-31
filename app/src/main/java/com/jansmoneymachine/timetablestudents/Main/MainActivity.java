package com.jansmoneymachine.timetablestudents.Main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.jansmoneymachine.timetablestudents.Deadlines.Deadlines;
import com.jansmoneymachine.timetablestudents.GroceryList.GroceryList;
import com.jansmoneymachine.timetablestudents.Notes.Notes;
import com.jansmoneymachine.timetablestudents.R;
import com.jansmoneymachine.timetablestudents.TimeTable.WeekOverview.WeekActivity;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar_Main;
    private ListView listView_Main;
    private CardItemDatabase cardItemDatabase = new CardItemDatabase();
    private MainActivity_CustomAdapterListView customAdapterListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }


    // Set up the view elements
    private void setUpView() {
        // Initializing toolbar
        toolbar_Main = (Toolbar) findViewById(R.id.toolbar_Main);
        setSupportActionBar(toolbar_Main);
        getSupportActionBar().setTitle("Student Organizer");

        // ListView
        listView_Main = (ListView) findViewById(R.id.listView_Main);
        customAdapterListView = new MainActivity_CustomAdapterListView(this, cardItemDatabase);
        listView_Main.setAdapter(customAdapterListView);
        listView_Main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    // TimeTable
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, WeekActivity.class);
                        startActivity(intent0);
                        break;

                    // Deadlines
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, Deadlines.class);
                        startActivity(intent1);
                        break;

                    // Notes
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, Notes.class);
                        startActivity(intent2);
                        break;

                    // Grocery List
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, GroceryList.class);
                        startActivity(intent3);
                        break;

                    // YouTube
                    case 4:
                        openYoutube();
                        break;

                    default:
                        break;
                }
            }
        });
    }


    // Opens YouTube channel from Daniel Jung: Mathe by Daniel Jung
    private void openYoutube() {
        // App
        Intent intentApp = new Intent(Intent.ACTION_VIEW);
        intentApp.setPackage("com.google.android.youtube");
        intentApp.setData(Uri.parse("https://www.youtube.com/user/beckuplearning"));

        // Web
        Intent intentWeb = new Intent(Intent.ACTION_VIEW);
        intentWeb.setData(Uri.parse("https://www.youtube.com/user/beckuplearning"));

        // First tries to open app, if not installed, opens YouTube in Web
        try {
            startActivity(intentApp);
        } catch (ActivityNotFoundException ex) {
            startActivity(intentWeb);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_main_work:
                Intent intent0 = new Intent(MainActivity.this, MainPopUpWork.class);
                startActivity(intent0);
                return true;

            case R.id.item_main_about:
                Intent intent1 = new Intent(MainActivity.this, MainPopUpAbout.class);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Solution -- MENU Symbol didn't exist
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
