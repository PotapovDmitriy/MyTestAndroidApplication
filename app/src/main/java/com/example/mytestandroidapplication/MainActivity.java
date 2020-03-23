package com.example.mytestandroidapplication;


import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickWriteText(View view) {
        TextView textView = findViewById(R.id.hello);
        textView.setText("Вот твой РЕЗУЛЬТАТ");
    }

    public void onClickDeleteText(View view) {
        TextView textView = findViewById(R.id.hello);
        textView.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
