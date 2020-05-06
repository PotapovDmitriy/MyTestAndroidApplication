package com.example.mytestandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvName, tvSecondName, tvMail, tvDOB, tvNum;
    public static String FLAG = "flag";
    Intent intent;
    Button btnBack;
    String flag;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String NAME = "name";
    public static String SNAME = "sname";
    public static String EMAIL = "email";
    public static String DOB = "dob";
    public static String NUM = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setTitle("О бо мне");

        intent = getIntent();
        tvName = findViewById(R.id.tvName);
        tvSecondName = findViewById(R.id.tvSecondName);
        tvMail = findViewById(R.id.tvMail);
        tvDOB = findViewById(R.id.tvDOB);
        tvNum = findViewById(R.id.tvNum);
        flag = intent.getStringExtra("flag");
        if (flag == null){
            loadInfo();
        }
        else {
            tvName.setText(sharedPreferences.getString(NAME, ""));
            tvSecondName.setText(sharedPreferences.getString(SNAME, ""));
            tvMail.setText(sharedPreferences.getString(EMAIL, ""));
            tvDOB.setText(sharedPreferences.getString(DOB, ""));
            tvNum.setText(sharedPreferences.getString(NUM, ""));
        }
        btnBack = findViewById(R.id.btnBackToMain);
        btnBack.setOnClickListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        editor.putString(NAME, tvName.getText().toString());
        editor.putString(SNAME, tvSecondName.getText().toString());
        editor.putString(EMAIL, tvMail.getText().toString());
        editor.putString(DOB, tvDOB.getText().toString());
        editor.putString(NUM, tvNum.getText().toString());

        editor.commit();
    }

    private void loadInfo() {
        tvName.setText(intent.getStringExtra("name"));
        tvSecondName.setText(intent.getStringExtra("sname"));
        tvMail.setText(intent.getStringExtra("email"));
        tvDOB.setText(intent.getStringExtra("dob"));
        tvNum.setText(intent.getStringExtra("number"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackToMain:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra(FLAG, "str");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_redact, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.redact:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
