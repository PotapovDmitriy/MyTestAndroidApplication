package com.example.mytestandroidapplication;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import org.json.JSONObject;


public class Widget extends AppWidgetProvider {
    private String city = "Коркино";
    private AppWidgetManager appMan;
    private RemoteViews remoteViews;
    private ComponentName componentName;

    int[] widgetIds;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        appMan = appWidgetManager;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        widgetIds = appWidgetIds;

        componentName = new ComponentName(context, Widget.class);
        String apiKey = "18d82c5a0c9f97611a9864ef7b3c2d34";
        AsyncWidgetRequest task = new AsyncWidgetRequest();
        task.execute(city, apiKey);
        System.out.println("Hello, I AM WIDGET!!!!!!!!!!!!!");
    }


    @SuppressLint("StaticFieldLeak")
    class AsyncWidgetRequest extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... arg) {
            return JsonRequest.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + arg[0] + "&appid=" + arg[1] + "&units=metric");
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                float tempF = (float) json.getJSONObject("main").getDouble("temp");
                float windF = (float) json.getJSONObject("wind").getInt("speed");
                remoteViews.setTextViewText(R.id.cityField, "ГОРОД: " + city);
                remoteViews.setTextViewText(R.id.tempField, "TEMP: " + tempF + "°С");
                remoteViews.setTextViewText(R.id.windField, "V of wind: " + windF + " м/с");
                appMan.updateAppWidget(widgetIds, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
