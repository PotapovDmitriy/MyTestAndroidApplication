package com.example.mytestandroidapplication;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements ChangeBackgroundColorDialog.ChangeBackgroundColorDialogListener {



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
        switch (item.getItemId()) {
            case R.id.line1:
                Toast.makeText(this, "Не надо так!!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.line3:
                Toast.makeText(this, "Сюда можно)))", Toast.LENGTH_SHORT).show();
                break;
            default:
                ChangeBackgroundColorDialog dialog = new ChangeBackgroundColorDialog();
                dialog.show(getSupportFragmentManager(), "ChangeBackgroundColorDialog");
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onDialogWhiteClick(DialogFragment dialog) {
        View myView = findViewById(R.id.container);
        myView.setBackgroundColor(R.color.white);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onDialogBlueClick(DialogFragment dialog) {
        View myView = findViewById(R.id.container);
        myView.setBackgroundColor(R.color.blue);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onDialogGreenClick(DialogFragment dialog) {
        View myView = findViewById(R.id.container);
        myView.setBackgroundColor(R.color.green);
    }
}

