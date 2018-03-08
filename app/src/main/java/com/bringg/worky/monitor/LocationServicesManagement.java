package com.bringg.worky.monitor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.bringg.worky.utils.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.concurrent.TimeUnit;

import timber.log.Timber;

/**
 * Created by Mickael on 08/03/2018.
 */
public class LocationServicesManagement implements MonitorInterface {

    private static final String TAG = LocationServicesManagement.class.getSimpleName();

    private FusedLocationProviderClient mFusedLocationClient;

    private Context context;

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Timber.tag(TAG).v("Received location");
        }
    };

    @Override
    public void startMonitoring(Context context) {
        this.context = context;

        startLocationUpdates();

    }

    @Override
    public void stopMonitoring() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    private LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(Constants.LocationServicesConstants.LOCATION_INTERVAL);
        mLocationRequest.setFastestInterval(Constants.LocationServicesConstants.LOCATION_FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return mLocationRequest;

    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        mFusedLocationClient.requestLocationUpdates(
                createLocationRequest(),
                mLocationCallback,
                null
        );
    }

}
