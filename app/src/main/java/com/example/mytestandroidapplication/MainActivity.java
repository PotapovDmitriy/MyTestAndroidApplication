package com.example.mytestandroidapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements ChangeBackgroundColorDialog.ChangeBackgroundColorDialogListener, View.OnClickListener {


    Button btnRnd;
    TextView tvRnd;
    private String flag;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRnd = findViewById(R.id.tvRnd);
        btnRnd = findViewById(R.id.btnRnd);
        btnRnd.setOnClickListener(this);

        intent = getIntent();
        flag = intent.getStringExtra("flag");
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
            case R.id.line5:
                if (!(flag == null)) {
                    Intent intent2 = new Intent(this, ThirdActivity.class);
                    startActivity(intent2);
                } else {
                    Intent intent = new Intent(this, SecondActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.line2:
                ChangeBackgroundColorDialog dialog = new ChangeBackgroundColorDialog();
                dialog.show(getSupportFragmentManager(), "ChangeBackgroundColorDialog");
                break;
            case R.id.line4:
                Intent intent2 = new Intent(this, SecondActivity.class);
                startActivity(intent2);

                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onDialogWhiteClick(DialogFragment dialog) {
        View myView = findViewById(R.id.container);
        myView.setBackgroundColor(R.color.white);
        dialog.dismiss();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onDialogBlueClick(DialogFragment dialog) {
        View myView = findViewById(R.id.container);
        myView.setBackgroundColor(R.color.blue);
        dialog.dismiss();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onDialogGreenClick(DialogFragment dialog) {
        View myView = findViewById(R.id.container);
        myView.setBackgroundColor(R.color.green);
        dialog.dismiss();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRnd:
                double a = Math.random();
                if (a > 0.5) {
                    double b = Math.random() * 100;
                    tvRnd.setText(Double.toString((int) b));
                } else {
                    double b = Math.random() * 10;
                    tvRnd.setText(Double.toString((int) b));
                }
                break;
            default:
                break;
        }
    }
}

