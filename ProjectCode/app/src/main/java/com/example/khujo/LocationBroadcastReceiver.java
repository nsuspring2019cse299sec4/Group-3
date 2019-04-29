package com.example.play.bazarmind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LocationBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("LocationBroadcast", "Service Stops! Oooooooooooooppppssssss!!!!");
        context.startService(new Intent(context, LocationMonitoringService.class));
    }
}
