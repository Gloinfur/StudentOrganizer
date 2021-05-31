package com.jansmoneymachine.timetablestudents.Deadlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.jansmoneymachine.timetablestudents.Main.MainActivity;
import com.jansmoneymachine.timetablestudents.R;
import java.util.ArrayList;


public class Deadlines extends AppCompatActivity {
    private Toolbar toolbar_Deadlines;
    private ListView listView_deadlines;
    private ArrayList<DeadlineItem> deadlinesList;
    private DeadlinesDbHelper deadlinesDbHelper;
    private DeadlinesListAdapter deadlinesListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines);

        setUpView();
        showDeadlinesItemList();
    }


    public void setUpView() {
        // Initializing Toolbar
        toolbar_Deadlines = (Toolbar) findViewById(R.id.toolbar_Deadlines);
        setSupportActionBar(toolbar_Deadlines);
        getSupportActionBar().setTitle("Deadlines");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initializing DBHelper, ListView, ArrayList which fills ListView
        deadlinesDbHelper = new DeadlinesDbHelper(this);
        listView_deadlines = (ListView) findViewById(R.id.listView_deadlines);
        deadlinesList = new ArrayList<>();
    }


    private void showDeadlinesItemList() {
        deadlinesList = deadlinesDbHelper.getDeadlinesList();

        if (deadlinesListAdapter == null) {
            deadlinesListAdapter = new DeadlinesListAdapter(this, deadlinesList);
            listView_deadlines.setAdapter(deadlinesListAdapter);
        } else {
            deadlinesListAdapter.getData().clear();
            deadlinesListAdapter.getData().addAll(deadlinesList);
            listView_deadlines.invalidateViews();
        }

        // ListView OnClick Item --> delete
        listView_deadlines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DeadlineItem item = (DeadlineItem) adapterView.getItemAtPosition(position);
                String description = item.getDeadlineDescription();
                deadlinesDbHelper.deleteDeadline(description);
                deadlinesListAdapter.getData().clear();
                deadlinesListAdapter.getData().addAll(deadlinesList);
                listView_deadlines.invalidateViews();
                showDeadlinesItemList();
            }
        });
    }


    // Solution - Menubuttons not shown in toolbar
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_deadlines, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                Intent intent0 = new Intent(Deadlines.this, MainActivity.class);
                startActivity(intent0);
                break;
            case R.id.item_add_deadline:
                Intent intent1 = new Intent(Deadlines.this, DeadlinesAddNewDeadlineItem.class);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
