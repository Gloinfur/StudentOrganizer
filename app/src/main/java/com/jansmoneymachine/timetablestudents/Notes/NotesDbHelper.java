package com.jansmoneymachine.timetablestudents.Notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class NotesDbHelper extends SQLiteOpenHelper {

    private static final String dbName = "NotesDB";
    private static final String tableName = "NotesItems";
    private static final String columnID = "ID";
    private static final String columnName = "Items";
    private static final int dbVersion = 1;

    public NotesDbHelper (Context context) {
        super(context, dbName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // %s = string placeholder; tableName and columnName will replace it

        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", tableName, columnName);
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If the table already exists, it will delete it and recreate it

        String query = String.format("DELETE TABLE IF EXISTS %s", tableName);
        db.execSQL(query);
        onCreate(db);
    }


    public void insertNewNote(String item) {
        // OnConflict_Replace = if a item with the same string already exists, it will get replaced

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnName, item);
        db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }


    public void deleteNote(String item) {
        // When hitting "delete" the placeholder `" = ?"´ will get replaced with the ID from the item to delete

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, columnName + " = ?", new String[] {item});
        db.close();
    }


    public ArrayList<String> getNotesList() {
        // Cursor moves through a string array which contains all the items from the given column
        // Cursor then moves through the string array as long as there are items he can move to (like the cursor in the musicPlayer)

        ArrayList<String> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName, new String[]{columnName}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int i = cursor.getColumnIndex(columnName);
            notesList.add(cursor.getString(i));
        }
        cursor.close();
        db.close();
        return notesList;
    }


    public Cursor getNoteID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + columnID + " FROM " + tableName + " WHERE " + columnName + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void updateNote(String newNote, int id, String oldNote) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + tableName + " SET " + columnName +
                " = '" + newNote + "' WHERE " + columnID + " = '" + id + "'" +
                " AND " + columnName + " = '" + oldNote + "'";
        db.execSQL(query);
    }
}
