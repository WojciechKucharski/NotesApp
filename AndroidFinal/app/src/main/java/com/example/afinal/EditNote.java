package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {
    private EditText titleET, contentET;
    private static DatabaseHelper db;

    private void showToast(String toast_content){
        Toast.makeText(getApplicationContext(), toast_content, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        titleET = findViewById(R.id.editText1);
        contentET = findViewById(R.id.editText2);
        titleET.setText(ShowNote.note.getTitle());
        contentET.setText(ShowNote.note.getContent());
        db = new DatabaseHelper(this, "DB_NAME", null, 1);
    }

    public void goBack(View view){finish();}

    public void updateNote(View view){
        Note newNote = new Note(titleET.getText().toString(), contentET.getText().toString());
        db.updateData(ShowNote.note, newNote.getTitle(), newNote.getContent());
        ShowNote.note = null;
        finish();
    }
    public void removeNote(View view){
        db.deleteData(ShowNote.note);
        ShowNote.note = null;
        finish();
    }
}