package com.example.khujo.utility;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.example.play.bazarmind.MyApplication;

import java.util.Map;




public class PreferenceHelper {
    private static final String TAG = "PreferenceHelper";

    /**
     * @param key   ( the Key to used to retrieve this data later  )
     * @param value ( any kind of primitive values  )
     *              <p/>
     *              non can be null!!!
     */
    @SuppressLint("ApplySharedPref")
    public static <T> void set(@NonNull String key, @Nullable T value) {
        if (InputHelper.isEmpty(key)) {
            throw new NullPointerException("Key must not be null! (key = " + key + "), (value = " + value + ")");
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).edit();
        if (InputHelper.isEmpty(value)) {
            clearKey(key);
            return;
        }
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Double) {
            edit.putLong(key, Double.doubleToRawLongBits((Double) value));
        } else {
            edit.putString(key, value.toString());
        }
        edit.commit();//apply on UI
    }

    @Nullable
    public static String getString(@NonNull String key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getString(key, null);
    }

    public static boolean getBoolean(@NonNull String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        return preferences.getAll().get(key) instanceof Boolean && preferences.getBoolean(key, false);
    }

    public static int getInt(@NonNull String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        return preferences.getAll().get(key) instanceof Integer ? preferences.getInt(key, 0) : -1;
    }

    public static long getLong(@NonNull String key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getLong(key, 0);
    }

    public static float getFloat(@NonNull String key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getFloat(key, 0);
    }

    public static double getDouble(@NonNull String key) {
        return Double.longBitsToDouble(PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getLong(key, Double.doubleToLongBits(0)));
    }

    public static void clearKey(@NonNull String key) {
        PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).edit().remove(key).apply();
    }

    public static boolean isExist(@NonNull String key) {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).contains(key);
    }

    public static void clearPrefs() {
        PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).edit().clear().apply();
    }

    public static Map<String, ?> getAll() {
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getAll();
    }
}
