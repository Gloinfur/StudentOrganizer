package com.jansmoneymachine.timetablestudents.Notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.jansmoneymachine.timetablestudents.R;
import java.util.ArrayList;


public class Notes extends AppCompatActivity {
    private Toolbar toolbar_Notes;
    private NotesDbHelper notesDbHelper;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView_notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        setUpView();
        showNoteItemList();
    }


    public void setUpView() {
        toolbar_Notes = (Toolbar) findViewById(R.id.toolbar_Notes);
        setSupportActionBar(toolbar_Notes);
        getSupportActionBar().setTitle("Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notesDbHelper = new NotesDbHelper(this);
        listView_notes = (ListView) findViewById(R.id.listView_notes);
    }


    private void showNoteItemList() {
        ArrayList<String> notesItemList = notesDbHelper.getNotesList();

        if (arrayAdapter == null) {
            arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_notes_item, R.id.txtView_notesItemName, notesItemList);
            listView_notes.setAdapter(arrayAdapter);
        } else {
            arrayAdapter.clear();
            arrayAdapter.addAll(notesItemList);
            arrayAdapter.notifyDataSetChanged();
        }

        listView_notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = adapterView.getItemAtPosition(position).toString();
                Cursor data = notesDbHelper.getNoteID(name);
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    showEditItemDialog(itemID, name);
                } else {
                    Toast.makeText(Notes.this, "No ID associated with that name.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void showEditItemDialog(int data, String note) {
        final String noteString = note;
        final int noteData = data;

        final EditText editTxt_item = new EditText(this);
        editTxt_item.setText(noteString);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit your note:")
                .setView(editTxt_item)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String note = String.valueOf(editTxt_item.getText());
                        notesDbHelper.updateNote(note, noteData, noteString);
                        showNoteItemList();
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notesDbHelper.deleteNote(noteString);
                        showNoteItemList();
                    }
                })
                .setNeutralButton("Share", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentShare = new Intent(Intent.ACTION_SEND);
                        intentShare.setType("text/plain");
                        intentShare.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        intentShare.putExtra(Intent.EXTRA_TEXT, noteString);
                        startActivity(Intent.createChooser(intentShare, ""));
                    }
                })
                .create();
        dialog.show();
    }


    private void showAddItemDialog() {
        final EditText editTxt_item = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new note:")
                .setView(editTxt_item)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String note = String.valueOf(editTxt_item.getText());
                        notesDbHelper.insertNewNote(note);
                        showNoteItemList();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }


    // Solution -- MENU Symbol didn't exist
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                onBackPressed();
                break;
            case R.id.item_add_note:
                showAddItemDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

