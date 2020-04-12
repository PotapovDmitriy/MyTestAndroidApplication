package com.example.mytestandroidapplication;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements ChangeBackgroundColorDialog.ChangeBackgroundColorDialogListener, View.OnClickListener {

    Button btnLoadInfo;
    TextView tvName, tvSecondName, tvMail, tvDOB, tvNum;
    Intent intent;

    public static String NAME = "name";
    public static String SNAME = "sname";
    public static String EMAIL = "email";
    public static String DOB = "dob";
    public static String NUM = "number";
    SharedPreferences sPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();
        tvName = findViewById(R.id.tvName);
        tvSecondName = findViewById(R.id.tvSecondName);
        tvMail = findViewById(R.id.tvMail);
        tvDOB = findViewById(R.id.tvDOB);
        tvNum = findViewById(R.id.tvNum);


        btnLoadInfo = findViewById(R.id.btnLoad);
        btnLoadInfo.setOnClickListener(this);
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
            case R.id.line4:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.line2:
                ChangeBackgroundColorDialog dialog = new ChangeBackgroundColorDialog();
                dialog.show(getSupportFragmentManager(), "ChangeBackgroundColorDialog");
                break;
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoad:
                tvName.setText(intent.getStringExtra("name"));
                tvSecondName.setText(intent.getStringExtra("sname"));
                tvMail.setText(intent.getStringExtra("email"));
                tvDOB.setText(intent.getStringExtra("dob"));
                tvNum.setText(intent.getStringExtra("number"));
                break;
            default:
                break;
        }
    }



}

