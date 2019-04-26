package com.example.play.khujo.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.example.play.khujo.LocationActivity;
import com.example.play.khujo.Main2Activity;
import com.example.play.khujo.MainActivity;
import com.example.play.khujo.MyApplication;
import com.example.play.khujo.R;
import com.example.play.khujo.data.Repository;
import com.example.play.khujo.models.Item;
import com.example.play.khujo.models.PlacesResponse;
import com.example.play.khujo.models.Result;
import com.example.play.khujokhujo.utility.PreferenceGetter;
import com.google.android.gms.location.LocationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Response;

public class LocationUpdatesIntentService extends IntentService {
    private static final String TAG = LocationUpdatesIntentService.class.getSimpleName();
    public static final String LOCATION_MAP_KEY = "location_map";
    public static final String OVER_LIMIT = "OVER_QUERY_LIMIT";
    public static final String STATUS_OK = "OK";
    private static final int LOCATION_RC = 6069;


    public static final String ACTION_PROCESS_UPDATES =
            "com.example.play.bazarmind.action" +
                    ".PROCESS_UPDATES";

    public LocationUpdatesIntentService() {
        super(TAG);
    }

    private NotificationManager mNotificationManager;

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATES.equals(action)) {
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null) {
                    List<Location> locations = result.getLocations();
                    Map<Result, List<Item>> results = getResults(locations);
                    if (results.size() > 0) {
                        showNotification(results);
                    }
                }
            }
        }
    }

    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(
                    Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    private String getLocationResultTitle() {
        return "Items available near you!";
    }

    private String getLocationResultText(Map<Result, List<Item>> results) {
        return "Items are available in " + results.size() + " places.";

    }

    void showNotification(Map<Result, List<Item>> results) {
        Intent locationIntent = new Intent(getApplicationContext(), LocationActivity.class);
        locationIntent.putExtra(LOCATION_MAP_KEY, convertToByteArray(results));

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

//        // Add the main Activity to the task stack as the parent.
//        stackBuilder.addParentStack(Main2Activity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntentWithParentStack(locationIntent);
        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(LOCATION_RC, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notificationBuilder = new Notification.Builder(MyApplication.getInstance())
                .setContentTitle(getLocationResultTitle())
                .setContentText(getLocationResultText(results))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setStyle(new Notification.BigTextStyle()
                        .bigText(getBigText(results)))
                .setContentIntent(notificationPendingIntent);
        getNotificationManager().notify(0, notificationBuilder.build());
    }

    private String getBigText(Map<Result, List<Item>> results) {
        return "We've found " + results.size() + " places near you where you can find your items. Tap to see them now.";
    }

    private Map<Result, List<Item>> getResults(List<Location> locations) {
        List<Result> results = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        try {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            Log.e(TAG, "getLocationResultText: User id: " + uid);
            Repository repository = Repository.getInstance();
            Response<Map<String, Object>> listResponse = repository.getLists(uid).execute();
            Map<String, Object> listMap = listResponse.body();

            for (Map.Entry<String, Object> entry : listMap.entrySet()) {
                Response<Map<String, Object>> itemResponse = repository.getItems(uid, entry.getKey()).execute();
                Map<String, Object> itemMap = itemResponse.body();
                for (Map.Entry<String, Object> entryItem : itemMap.entrySet()) {
                    Response<Item> itemResponse1 = repository.getItem(uid, entry.getKey(), entryItem.getKey()).execute();
                    Item item = itemResponse1.body();
                    items.add(item);
                }
            }
            List<PlacesResponse> responses = new ArrayList<>();
            String location = locations.get(0).getLatitude() + "," + locations.get(0).getLongitude();
            Collections.shuffle(items);
            for (int i = 0; i < items.size(); i++) {
                Log.e(TAG, "getLocationResultText: Start: " + System.currentTimeMillis());
                long sleepTime = 90000 - (System.currentTimeMillis() - PreferenceGetter.getLastOverLimit());
                if (sleepTime < 0) {
                    sleepTime = 0;
                }
                Log.e(TAG, "getLocationResultText: Sleeptime: " + sleepTime);
                Thread.sleep(sleepTime);
                Log.e(TAG, "getLocationResultText: End: " + System.currentTimeMillis());
                Response<PlacesResponse> placesResponseResponse = repository.getNearbyPlaces(location, items.get(i).getPlaceType(), PreferenceGetter.getRadius()).execute();
                PlacesResponse placesResponse = placesResponseResponse.body();
                if (placesResponse != null) {
                    Log.e(TAG, "getResults: Status: "+placesResponse.getStatus());
                }
                if (placesResponse != null && OVER_LIMIT.equalsIgnoreCase(placesResponse.getStatus())) {
                    Log.e(TAG, "getResults: WEW BOY "+placesResponse.getStatus() );
                    PreferenceGetter.setLastOverLimit(System.currentTimeMillis());
                    i--;
                } else if (placesResponse != null && STATUS_OK.equalsIgnoreCase(placesResponse.getStatus())){
                    responses.add(placesResponse);
                }
            }

            for (PlacesResponse placesResponse : responses) {
                for (Result result : placesResponse.getResults()) {
                    if (!results.contains(result)) {
                        results.add(result);
                    }
                }
            }
            Log.e(TAG, "getLocationResultText: Result size: " + results.size());


        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            Log.e(TAG, "Service not available", ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            Log.e(TAG, "Invalid locations.get(0) values" + ". " +
                    "Latitude = " + locations.get(0).getLatitude() +
                    ", Longitude = " +
                    locations.get(0).getLongitude(), illegalArgumentException);
        } catch (Exception e) {
            Log.e(TAG, "getLocationResultText: WTF", e);
        }
        Map<Result, List<Item>> resultListMap = new HashMap<>();
        for (Result result : results) {
            List<Item> itemsForResult = new ArrayList<>();
            for (Item item : items) {
                if (result.getTypes().contains(item.getPlaceType())) {
                    itemsForResult.add(item);
                }
            }
            resultListMap.put(result, itemsForResult);
        }
        return resultListMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<Result, List<Item>> convertToMap(byte[] mapByte) {
        if (mapByte == null) {
            return new HashMap<>();
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(mapByte);
        ObjectInput in = null;
        Map<Result, List<Item>> resultListMap = new HashMap<>();
        try {
            in = new ObjectInputStream(bis);
            resultListMap = (Map<Result, List<Item>>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return resultListMap;
    }

    public static byte[] convertToByteArray(Map<Result, List<Item>> resultListMap) {
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(resultListMap);
            out.flush();
            data = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }
}
