package com.jansmoneymachine.timetablestudents.GroceryList;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.jansmoneymachine.timetablestudents.R;
import java.util.ArrayList;

public class GroceryList extends AppCompatActivity {
    private GroceryDbHelper groceryDbHelper;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView_grocery;
    private Toolbar toolbar_GroceryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocerylist);

        setUpView();
        showGroceryItemList();
    }


    public void setUpView() {
        toolbar_GroceryList = (Toolbar) findViewById(R.id.toolbar_GroceryList);
        setSupportActionBar(toolbar_GroceryList);
        getSupportActionBar().setTitle("Grocery List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        groceryDbHelper = new GroceryDbHelper(this);
        listView_grocery = (ListView) findViewById(R.id.listView_grocery);
    }


    private void showGroceryItemList() {
        ArrayList<String> groceryItemList = groceryDbHelper.getGroceryList();

        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_grocerylist_item, R.id.txtView_groceryListItemName, groceryItemList);
            listView_grocery.setAdapter(arrayAdapter);
        } else {
            arrayAdapter.clear();
            arrayAdapter.addAll(groceryItemList);
            arrayAdapter.notifyDataSetChanged();
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                onBackPressed();
                break;
            case R.id.item_add_item:
                showAddItemDialog();
                return true;
            case R.id.item_share_list:
                shareList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showAddItemDialog() {
        final EditText editTxt_item = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new item:")
                .setMessage("What´s next on your list?")
                .setView(editTxt_item)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = String.valueOf(editTxt_item.getText());
                        groceryDbHelper.insertNewItem(item);
                        showGroceryItemList();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }


    public void checkBoxClicked(View view) {
        View parent = (View) view.getParent();
        TextView txtView_groceryListItem = (TextView) parent.findViewById(R.id.txtView_groceryListItemName);
        if (txtView_groceryListItem.getPaint().isStrikeThruText() == false) {
            txtView_groceryListItem.setPaintFlags(txtView_groceryListItem.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            txtView_groceryListItem.setPaintFlags(txtView_groceryListItem.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }


    public void deleteItem(View view) {
        View parent = (View) view.getParent();
        TextView txtView_groceryListItem = (TextView) parent.findViewById(R.id.txtView_groceryListItemName);
        String groceryListItem = String.valueOf(txtView_groceryListItem.getText());
        groceryDbHelper.deleteItem(groceryListItem);
        showGroceryItemList();
    }


    public void shareList() {
        ArrayList<String> groceryItemList = groceryDbHelper.getGroceryList();
        String listToShare = "My Grocery List: \n";

        for (int i = 0; i < groceryItemList.size(); i++) {
            listToShare = listToShare + "\n - " + groceryItemList.get(i);
        }

        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intentShare.putExtra(Intent.EXTRA_TEXT, listToShare);
        startActivity(Intent.createChooser(intentShare, ""));
    }


    // Solution -- MENU Symbol didn't exist
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grocerylist, menu);
        return true;
    }
}
