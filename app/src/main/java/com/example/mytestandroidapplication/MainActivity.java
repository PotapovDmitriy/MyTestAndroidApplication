package com.example.mytestandroidapplication;


import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.line1:
                Toast.makeText(this, "Не надо так!!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.line3:
                Toast.makeText(this, "Сюда можно)))", Toast.LENGTH_SHORT).show();
                break;
            default:
                Dialog dialog = new Dialog();
                dialog.show(getSupportFragmentManager(),"Notice");
        }
        return  super.onOptionsItemSelected(item);
    }

}
