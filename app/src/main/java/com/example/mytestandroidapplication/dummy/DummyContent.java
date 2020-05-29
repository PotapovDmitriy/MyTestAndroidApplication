package com.example.mytestandroidapplication.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    public static final List<DummyContent.WeatherDateItem> ITEMS = new ArrayList<DummyContent.WeatherDateItem>();

    public static final Map<String, DummyContent.WeatherDateItem> ITEM_MAP = new HashMap<String, DummyContent.WeatherDateItem>();

    private static final int COUNT = 25;

    public static void addItem(DummyContent.WeatherDateItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.date, item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class WeatherDateItem {
        public int id = 1;
        public final String date;
        public final String city;
        public final float temp;
        public final float wind;

        public WeatherDateItem(String date, String city, float temp, float wind) {
            this.id = id + 1;
            this.date = date;
            this.city = city;
            this.temp = temp;
            this.wind = wind;
        }

        @Override
        public String toString() {
            return city;
        }
    }
}
