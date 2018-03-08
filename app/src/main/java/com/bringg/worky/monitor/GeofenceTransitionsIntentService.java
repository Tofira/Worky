package com.bringg.worky.monitor;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.bringg.worky.data.db.AppDatabase;
import com.bringg.worky.utils.SharedPrefUtils;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.Calendar;

import timber.log.Timber;

/**
 * Created by Mickael on 06/03/2018.
 */
public class GeofenceTransitionsIntentService extends IntentService {

    private static final String TAG = GeofenceTransitionsIntentService.class.getSimpleName();

    public GeofenceTransitionsIntentService() {
        super(GeofenceTransitionsIntentService.class.getSimpleName());
    }

    protected void onHandleIntent(Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Timber.tag(TAG).e("Error - " + geofencingEvent.getErrorCode());
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)
        {
            Timber.tag(TAG).i("Received event - " + geofenceTransition);

            //If entrance time is already set -> we received two Entrance events in a row -> raise an exception
            if(SharedPrefUtils.getInstance(this).getEntranceTime() != null)
            {
                AppDatabase.addExceptionSession(this, true);
                return;
            }

            SharedPrefUtils.getInstance(this).setEntranceTime(Calendar.getInstance().getTime());


        }
        else if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT)
        {
            AppDatabase.fillLastSession(getBaseContext());

            Timber.tag(TAG).i("Received event - " + geofenceTransition);
        }
        else
            // Log the error.
            Timber.tag(TAG).e("Invalid type" + geofenceTransition);
    }
}