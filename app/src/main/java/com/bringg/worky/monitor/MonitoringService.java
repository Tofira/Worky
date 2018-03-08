package com.bringg.worky.monitor;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bringg.worky.utils.Constants;
import com.bringg.worky.utils.GeneralUtils;
import com.google.android.gms.location.GeofencingEvent;

public class MonitoringService extends Service  {

    private static final String TAG = MonitoringService.class.getSimpleName();

    private GeofencesManagement geofencesManagement;

    private LocationServicesManagement locationServicesManagement;

    public static void startService(Context context) {
        Intent intent = new Intent(context, MonitoringService.class);
        context.startService(intent);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, MonitoringService.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(Constants.NotificationsConstants.NOTIFICATION_ID, GeneralUtils.getOngoingNotification(getBaseContext()));
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onCreate() {
        super.onCreate();

        startMonitoringLocation();

        startMonitoringMovement();
    }


    private void startMonitoringLocation()
    {
        locationServicesManagement = new LocationServicesManagement();
        locationServicesManagement.startMonitoring(this);
    }

    private void startMonitoringMovement()
    {
        geofencesManagement = new GeofencesManagement();
        geofencesManagement.startMonitoring(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        locationServicesManagement.stopMonitoring();

        geofencesManagement.stopMonitoring();

        stopSelf();
    }


}
