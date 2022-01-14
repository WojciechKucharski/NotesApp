package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NewNote extends AppCompatActivity {
    private EditText titleET, contentET;
    private static DatabaseHelper db;

    private void showToast(String toast_content){
        Toast.makeText(getApplicationContext(), toast_content, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        titleET = findViewById(R.id.editText1);
        contentET = findViewById(R.id.editText2);
        db = new DatabaseHelper(this, "DB_NAME", null, 1);
    }

    public void goBack(View view){finish();}

    public void addNote(View view){
        Note newNote = new Note(String.valueOf(titleET.getText()), String.valueOf(contentET.getText()));
        if (db.insertData(newNote)){
            showToast("Note added");
        }
        else{
            showToast("Note NOT added");
        }
        finish();
    }



}