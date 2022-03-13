package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ShowNote extends AppCompatActivity {
    private ListView NoteListView;
    public static Note note;
    private static DatabaseHelper db;
    private static boolean sorting_order = false;

    private void showToast(String toast_content){
        Toast.makeText(getApplicationContext(), toast_content, Toast.LENGTH_LONG).show();
    }

    private String sortingOrder(){
        if(sorting_order) {
            sorting_order = false;
            return "DESC";
        }
        else {
            sorting_order = true;
            return "ASC";
        }
    }

    public void loadAllSortById(View view){
        this.loadAll("ID " + sortingOrder());
    }
    public void loadAllSortByTitle(View view){
        this.loadAll("TITLE " + sortingOrder());
    }
    public void loadAllSortByContent(View view){
            this.loadAll("CONTENT " + sortingOrder());
    }

    public void loadAll(){this.loadAll("ID DESC");}

    public void loadAll(String sort_arg){
        try{
            loadFromDB(sort_arg);
            setupListeners();
            setAdapter();
        }
        catch(Exception e){
            showToast(e.toString());
        }
    }


    private void setupListeners(){
        NoteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                note = (Note) NoteListView.getItemAtPosition(position);
                startActivity(new Intent(getApplicationContext(), EditNote.class));
            }
        });
    }
    private void loadFromDB(String sort_arg){
        Note.noteList.clear();
        Cursor cursor = db.viewData(sort_arg);
        if (cursor.getCount() == 0){
            showToast("Notepad is empty");
        }
        else if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Note newNote = new Note(cursor.getString(1), cursor.getString(2), cursor.getString(0));
                cursor.moveToNext();
            }
        }
    }
    public void goBack(View view){startActivity(new Intent(this,MainActivity.class));}
    public void go2NewNote(View view){startActivity(new Intent(this, NewNote.class));}

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadAll();
    }
    private void setAdapter()
    {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.noteList);
        NoteListView.setAdapter(noteAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        db = new DatabaseHelper(this, "DB_NAME", null, 1);
        NoteListView = findViewById(R.id.ListView);

        loadAll();
    }
}