package com.bringg.worky.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.bringg.worky.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * Created by Mickael on 3/6/2018.
 */

public class PermissionsUtils {

    public interface PermissionGrantedListener {
        void onPermissionGranted();
        void onPermissionRefused();
    }

    public static void requestLocationPermissions(final Activity activity, final PermissionGrantedListener permissionGrantedListener)
    {

        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        if(permissionGrantedListener != null)
                            permissionGrantedListener.onPermissionGranted();

                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        if(permissionGrantedListener != null)
                            permissionGrantedListener.onPermissionRefused();

                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

}
