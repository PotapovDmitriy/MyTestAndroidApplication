package com.example.mytestandroidapplication;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Camera cam;
    private boolean lightOn = false;
    private Thread t;

    static TextView city, temp, windSp, tvWeather;
    EditText etCity;
    Button showBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        windSp = findViewById(R.id.windSp);
        etCity = findViewById(R.id.etCity);
        showBTN = findViewById(R.id.show_weather);
        tvWeather = findViewById(R.id.tvWeather);
    }


    protected void Request(String cityName, String apiKey) {

    }

    protected static String readAll(Reader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int curretChar;
        while ((curretChar = bufferedReader.read()) != -1) {
            stringBuilder.append((char) curretChar);
        }
        return stringBuilder.toString();
    }

    protected static JSONObject readJsonFromUrl(String url) {
        JSONObject json = null;

        try (InputStream inputStream = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);
            json = new JSONObject(jsonText);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    @SuppressLint("ShowToast")
    public void onShowWeatherClick(View view) {
        System.out.println(etCity.length() == 0);
        if (etCity.length() == 0) {
            temp.setText("Поле \"Город\" пустое");
        } else {
            String apiKey = "18d82c5a0c9f97611a9864ef7b3c2d34";
            String cityName = etCity.getText().toString();
            Toast.makeText(this, cityName, Toast.LENGTH_SHORT);
            System.out.println("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey);

            MainActivity.AsyncRequest task = new MainActivity.AsyncRequest();
            task.execute(cityName, apiKey);
            etCity.clearFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
        }
    }

    static class AsyncRequest extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... arg) {
            return readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + arg[0] + "&appid=" + arg[1] + "&units=metric");
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                float tempF = (float) json.getJSONObject("main").getDouble("temp");
                float windF = (float) json.getJSONObject("wind").getInt("speed");
                JSONArray weather = json.getJSONArray("weather");
                String description = weather.getJSONObject(0).getString("description");
                System.out.println(description);
                temp.setText("Температура: " + tempF + "°С");
                windSp.setText("Скорость ветра: " + windF + " м/с");
                tvWeather.setText(description);

            } catch (Exception e) {
                temp.setText("Неверный город");
                e.printStackTrace();
            }
        }
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
        }
        return super.onOptionsItemSelected(item);
    }
}

