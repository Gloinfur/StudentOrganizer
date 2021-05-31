package com.jansmoneymachine.timetablestudents.Deadlines;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DeadlinesDbHelper extends SQLiteOpenHelper {

    private static final String dbName = "deadlinesDB";
    private static final int dbVersion = 1;
    private final Context context;
    private static final String tableName = "DeadlineItems";
    private static final String columnID = "ID";
    private static final String columnDescription = "Description";
    private static final String columnDate = "Date";
    private static final String columnTime = "Time";
    private static final String columnReminder = "Reminder";


    public DeadlinesDbHelper(Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", tableName, columnID, columnDescription, columnDate, columnTime, columnReminder);
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(query);
        onCreate(db);
    }


    public void insertNewDeadline(String description, String date, String time, String reminder) {
        // OnConflict_Replace = if a item with the same string already exists, it will get replaced

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnDescription, description);
        values.put(columnDate, date);
        values.put(columnTime, time);
        values.put(columnReminder, reminder);
        db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }


    public void deleteDeadline(String item) {
        // When hitting "delete" the placeholder `" = ?"´ will get replaced with the ID from the item to delete

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, columnDescription + " = ?", new String[]{item});
        db.close();
    }


    public ArrayList<DeadlineItem> getDeadlinesList() {

        ArrayList<DeadlineItem> deadlinesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        while (cursor.moveToNext()) {
            String description = cursor.getString(1);
            String date = cursor.getString(2);
            String time = cursor.getString(3);
            String reminder = cursor.getString(4);

            deadlinesList.add(new DeadlineItem(description, date, time, reminder));
        }
        cursor.close();
        db.close();
        return deadlinesList;
    }


    public Cursor getDeadlineID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        //String query = "SELECT " + columnID + " FROM " + tableName + " WHERE " + columnDescription + " = '" + name + "'";
        String query = "SELECT " + columnID + " FROM " + tableName + " WHERE " + columnID + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
