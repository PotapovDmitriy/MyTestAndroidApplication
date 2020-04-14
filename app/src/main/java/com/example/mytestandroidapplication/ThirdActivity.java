package com.example.mytestandroidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {


    TextView tvName, tvSecondName, tvMail, tvDOB, tvNum;
    public static String FLAG = "flag";
    Intent intent;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        intent = getIntent();
        tvName = findViewById(R.id.tvName);
        tvSecondName = findViewById(R.id.tvSecondName);
        tvMail = findViewById(R.id.tvMail);
        tvDOB = findViewById(R.id.tvDOB);
        tvNum = findViewById(R.id.tvNum);
        loadInfo();
        btnBack = findViewById(R.id.btnBackToMain);
        btnBack.setOnClickListener(this);
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
}
