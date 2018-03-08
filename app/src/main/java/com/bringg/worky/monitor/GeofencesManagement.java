package com.bringg.worky.monitor;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.bringg.worky.R;
import com.bringg.worky.data.address.AddressCoord;
import com.bringg.worky.utils.Constants;
import com.bringg.worky.utils.SharedPrefUtils;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Collections;

/**
 * Created by Mickael on 3/6/2018.
 */

public class GeofencesManagement implements MonitorInterface{

    private static final String TAG = GeofencesManagement.class.getSimpleName();

    private GeofencingClient mGeofencingClient;

    private Context context;

    private Geofence geofence;

    private PendingIntent pendingIntent;

    private GeofencingRequest geofencingRequest;



    public void startMonitoring(Context context)
    {

        this.context = context;

        mGeofencingClient = LocationServices.getGeofencingClient(context);

        setupGeofenceInstance();

        addGeofence();

    }

    @Override
    public void stopMonitoring() {
        mGeofencingClient.removeGeofences(getGeofencePendingIntent())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @SuppressLint("MissingPermission")
    private void addGeofence()
    {
        mGeofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, R.string.monitoring_error, Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void setupGeofenceInstance()
    {

        AddressCoord userAddress = SharedPrefUtils.getInstance(context).getUserAddress();

        geofence = new Geofence.Builder()
            // Set the request ID of the geofence. This is a string to identify this
            // geofence.
            .setRequestId(Constants.GeofencesConstants.GEOFENCE_REQUEST_ID)

            .setCircularRegion(
                userAddress.getUserLat(), //latitude
                userAddress.getUserLongitude(), //longitude
                Constants.GeofencesConstants.GEOFENCE_RADIUS
            )
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                    Geofence.GEOFENCE_TRANSITION_EXIT)
            .build();

    }

    private GeofencingRequest getGeofencingRequest() {

        if(geofencingRequest != null) {
            return geofencingRequest;
        }

        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(Collections.singletonList(geofence));

        geofencingRequest = builder.build();

        return geofencingRequest;
    }

    private PendingIntent getGeofencePendingIntent() {

        if (pendingIntent != null) {
            return pendingIntent;
        }

        Intent intent = new Intent(context, GeofenceTransitionsIntentService.class);

        pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }

}
