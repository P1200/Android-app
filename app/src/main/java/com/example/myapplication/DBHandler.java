package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "note_extended";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "notes";
    private static final String ID_COL = "id";
    private static final String PATH_COL = "path";
    private static final String NOTE_TYPE_COL = "note_type";
    private static final String CREATION_DATE_COL = "creation_date";
    private static final String MODIFICATION_DATE_COL = "modification_date";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PATH_COL + " TEXT,"
                + NOTE_TYPE_COL + " TEXT,"
                + CREATION_DATE_COL + " TEXT,"
                + MODIFICATION_DATE_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewNote(String notePath, String noteType, String creationDate, String modificationDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PATH_COL, notePath);
        values.put(NOTE_TYPE_COL, noteType);
        values.put(CREATION_DATE_COL, creationDate);
        values.put(MODIFICATION_DATE_COL, modificationDate);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addNewNote(String notePath, String noteType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PATH_COL, notePath);
        values.put(NOTE_TYPE_COL, noteType);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<NotesModal> readNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorNotes = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<NotesModal> notesModalArrayList = new ArrayList<>();
        if (cursorNotes.moveToFirst()) {
            do {
                notesModalArrayList.add(new NotesModal(cursorNotes.getString(1),
                        cursorNotes.getString(2),
                        cursorNotes.getString(3),
                        cursorNotes.getString(4)));
            } while (cursorNotes.moveToNext());
        }
        cursorNotes.close();
        return notesModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

