package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "NOTES_DB";
    private static final String DB_TABLE = "NOTES_TB";

    private static final String ID = "ID";
    private static final String TITLE = "TITLE";
    private static final String CONTENT = "CONTENT";

    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITLE + " TEXT, " + CONTENT + " TEXT " + ")";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE , null);
        return cursor;
    }

    public Cursor viewData(String sort_arg) {
        try{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE + " ORDER BY " + sort_arg, null);
        return cursor;
        }
        catch(Exception e){
            return this.viewData();
        }
    }

    public boolean insertData(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, note.getTitle());
        contentValues.put(CONTENT, note.getContent());
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }

    public boolean deleteData(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorEdit = db.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE ID = ?", new String[]{note.getID()});
        cursorEdit.moveToFirst();
        return db.delete(DB_TABLE, "ID =?", new String[]{cursorEdit.getString(0)}) > 0;
    }

    public boolean updateData(Note note, String newTitle, String newContent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursorEdit = db.rawQuery("SELECT * FROM " + DB_TABLE + " WHERE ID = ?", new String[]{note.getID()});
        cursorEdit.moveToFirst();
        contentValues.put(TITLE, newTitle);
        contentValues.put(CONTENT, newContent);
        long result = db.update(DB_TABLE, contentValues, "ID =?", new String[]{cursorEdit.getString(0)});
        return result != -1;
    }
}