package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private void showToast(String toast_content){
        Toast.makeText(getApplicationContext(), toast_content, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go2Notes(View view){startActivity(new Intent(this, ShowNote.class));}
    public void go2Aboutme(View view){startActivity(new Intent(this, AboutMe.class));}
}