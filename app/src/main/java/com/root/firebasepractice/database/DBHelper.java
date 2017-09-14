package com.root.firebasepractice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.root.firebasepractice.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14/9/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "messageManager";
    private static final String TABLE_MESSAGES = "messages";
    private static final String KEY_MESSAGES = "message";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES + "("
                + KEY_MESSAGES + " TEXT,"
                + ")";
        db.execSQL(CREATE_MESSAGES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }
    void addMessage(Messages message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MESSAGES, message.getMESSAGE()); // Contact Name
        db.insert(TABLE_MESSAGES, null, values);
        db.close(); // Closing database connection
    }
    public List<Messages> getAllMessages() {
        List<Messages> messagesList = new ArrayList<Messages>();
        String selectQuery = "SELECT  * FROM " + TABLE_MESSAGES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Messages message = new Messages();
                message.setMESSAGE((cursor.getString(0)));
                messagesList.add(message);
            } while (cursor.moveToNext());
        }
        return messagesList;
    }
}
