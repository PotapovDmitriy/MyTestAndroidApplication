package com.example.mytestandroidapplication;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import org.json.JSONObject;

public class Widget extends AppWidgetProvider {
    private String city = "Korkino";
    private String apiKey;
    private AppWidgetManager appMan;
    private RemoteViews remoteViews;
    private ComponentName componentName;
    private int REQUEST_CODE_PERMISSION_INTERNET = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        System.out.println("hello i am widget");
        appMan = appWidgetManager;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        componentName = new ComponentName(context, Widget.class);
        apiKey = "18d82c5a0c9f97611a9864ef7b3c2d34";
        remoteViews.setTextViewText(R.id.cityField, "Сначала запросите погоду в приложении");
        remoteViews.setTextViewText(R.id.tempField, "");
        remoteViews.setTextViewText(R.id.windField, "");
    }


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
                remoteViews.setTextViewText(R.id.cityField, city);
                remoteViews.setTextViewText(R.id.tempField, tempF + " С°");
                remoteViews.setTextViewText(R.id.windField, windF + " м/с");
                appMan.updateAppWidget(componentName, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}