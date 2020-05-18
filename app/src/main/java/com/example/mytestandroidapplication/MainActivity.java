package com.example.mytestandroidapplication;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements ChangeBackgroundColorDialog.ChangeBackgroundColorDialogListener, View.OnClickListener {


    Button btnRnd;
    TextView tvRnd;
    private String flag;
    Intent intent;
    public static String FLAG = "flag";
    private Camera cam;
    private boolean lightOn = false;
    private Thread t;


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

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Camera permission not found", Toast.LENGTH_SHORT);
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA}, 0);

        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.line1:

                Intent intent1 = new Intent(this, WeatherActivity.class);
                startActivity(intent1);

                break;
            case R.id.light:
                if (cam == null) {
                    checkPermission();
                    cam = Camera.open();
                }
                t = new Thread(new Runnable() {
                    public void run() {
                        Camera.Parameters p = cam.getParameters();
                        if (lightOn) {
                            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            lightOn = false;
                        } else {
                            lightOn = true;
                            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        }
                        cam.setParameters(p);
                        cam.startPreview();
                    }
                });
                t.start();
                break;
            case R.id.line5:
                if (!(flag == null)) {
                    Intent intent2 = new Intent(this, ThirdActivity.class);
                    intent2.putExtra(FLAG, "str");
                    startActivity(intent2);
                } else {
                    Intent intent2 = new Intent(this, SecondActivity.class);
                    startActivity(intent2);
                }
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

