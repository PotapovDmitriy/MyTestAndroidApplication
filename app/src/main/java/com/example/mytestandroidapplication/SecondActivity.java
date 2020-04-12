package com.example.mytestandroidapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {


    EditText etName, etSecondName, etMail, etDOB, etNum;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button btnSave, btnBack, btnClr;
    Intent intent;

    public static String NAME = "name";
    public static String SNAME = "sname";
    public static String EMAIL = "email";
    public static String DOB = "dob";
    public static String NUM = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        etName = findViewById(R.id.etName);
        etSecondName = findViewById(R.id.etSName);
        etMail = findViewById(R.id.etMail);
        etDOB = findViewById(R.id.etDOB);
        etNum = findViewById(R.id.etNum);

        intent = new Intent(this, MainActivity.class);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBackToMain);
        btnBack.setOnClickListener(this);
        btnClr = findViewById(R.id.btnClear);
        btnClr.setOnClickListener(this);

        LoadText();

    }

    private void LoadText() {

        sharedPreferences = getPreferences(MODE_PRIVATE);

        etName.setText(sharedPreferences.getString(NAME, ""));
        etSecondName.setText(sharedPreferences.getString(SNAME, ""));
        etMail.setText(sharedPreferences.getString(EMAIL, ""));
        etDOB.setText(sharedPreferences.getString(DOB, ""));
        etNum.setText(sharedPreferences.getString(NUM, ""));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveText();
                Toast.makeText(this, "Данные сохранены и отправленны на главную страницу", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etSecondName.setText("");
                etDOB.setText("");
                etNum.setText("");
                etMail.setText("");
                break;
            case R.id.btnBackToMain:
                startActivity(intent);
                break;
            case R.id.btnClear:
                etName.setText("");
                etSecondName.setText("");
                etDOB.setText("");
                etNum.setText("");
                etMail.setText("");
                break;
            default:
                break;
        }
    }


    private void saveText() {
        intent.putExtra(NAME, etName.getText().toString());
        intent.putExtra(SNAME, etSecondName.getText().toString());
        intent.putExtra(DOB, etDOB.getText().toString());
        intent.putExtra(NUM, etNum.getText().toString());
        intent.putExtra(EMAIL, etMail.getText().toString());

        editor.putString(NAME, etName.getText().toString());
        editor.putString(SNAME, etSecondName.getText().toString());
        editor.putString(EMAIL, etMail.getText().toString());
        editor.putString(DOB, etDOB.getText().toString());
        editor.putString(NUM, etNum.getText().toString());

        editor.commit();
    }


}