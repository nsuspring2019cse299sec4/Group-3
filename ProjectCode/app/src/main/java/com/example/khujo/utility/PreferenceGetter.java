package com.example.khujo.utility;


public class PreferenceGetter {

    private static final String PREF_LAST_OVER_LIMIT_TIME = "last_limit_over_time";
    private static final String PREF_LOCATION_AREA = "location_area";

    public static void setLastOverLimit(long time) {
        PreferenceHelper.set(PREF_LAST_OVER_LIMIT_TIME, time);
    }

    public static long getLastOverLimit() {
        return PreferenceHelper.getLong(PREF_LAST_OVER_LIMIT_TIME);
    }

    public static void setLocationArea(int area) {
        PreferenceHelper.set(PREF_LOCATION_AREA, area);
    }

    public static int getLocationArea() {
        int area = PreferenceHelper.getInt(PREF_LOCATION_AREA);
        if (area <= 0) {
            return 3;
        }
        return area;
    }

    public static String getRadius() {
        switch (getLocationArea()) {
            case 1: return "1000";
            case 2: return "2000";
            default: return "3000";
        }
    }

}
