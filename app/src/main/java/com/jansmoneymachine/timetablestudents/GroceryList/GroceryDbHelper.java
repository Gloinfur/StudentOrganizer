package com.jansmoneymachine.timetablestudents.GroceryList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class GroceryDbHelper extends SQLiteOpenHelper {

    private static final String dbName = "GroceryDB";
    private static final String tableName = "GroceryItems";
    private static final String columnName = "Items";
    private static final int dbVersion = 1;

    public GroceryDbHelper(Context context) {
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


    public void insertNewItem(String item) {
        // OnConflict_Replace = if a item with the same string already exists, it will get replaced

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(columnName, item);
        db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }


    public void deleteItem(String item) {
        // When hitting "delete" the placeholder `" = ?"´ will get replaced with the ID from the item to delete

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, columnName + " = ?", new String[]{item});
        db.close();
    }


    public ArrayList<String> getGroceryList() {
        // Cursor moves through a string array which contains all the items from the given column
        // Cursor then moves through the string array as long as there are items he can move to (like the cursor in the musicPlayer)

        ArrayList<String> groceryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tableName, new String[]{columnName}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int i = cursor.getColumnIndex(columnName);
            groceryList.add(cursor.getString(i));
        }
        cursor.close();
        db.close();
        return groceryList;
    }
}
