package com.bringg.worky.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bringg.worky.data.address.AddressCoord;

import java.util.Date;

/**
 * Created 06.03.2018 by
 *
 * @author Mickael
 */
public class SharedPrefUtils {

    private static volatile SharedPrefUtils instance;

    private final SharedPreferences preferences;

    private SharedPrefUtils(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPrefUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPrefUtils.class) {
                if (instance == null) {
                    instance = new SharedPrefUtils(context);
                }
            }
        }
        return instance;
    }

    public void setUserAddress(AddressCoord addressCoord)
    {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putFloat(Constants.SHARED_PREFS.LOCATION_LAT_KEY,
                (float) addressCoord.getUserLat());

        editor.putFloat(Constants.SHARED_PREFS.LOCATION_LONGITUDE_KEY,
                (float) addressCoord.getUserLongitude());

        editor.apply();
    }

    public AddressCoord getUserAddress()
    {

        double userLat = preferences.getFloat(Constants.SHARED_PREFS.LOCATION_LAT_KEY,
                Constants.SHARED_PREFS.LOCATION_LAT_DEFAULT);

        double userLongitude = preferences.getFloat(Constants.SHARED_PREFS.LOCATION_LONGITUDE_KEY,
                Constants.SHARED_PREFS.LOCATION_LONGITUDE_DEFAULT);

        return new AddressCoord(userLat, userLongitude);

    }

    public void setEntranceTime(Date date) {
        preferences.edit()
                .putLong(Constants.SHARED_PREFS.ENTRANCE_KEY, date.getTime())
                .apply();
    }

    public void clearEntranceTime()
    {
        preferences.edit()
                .putLong(Constants.SHARED_PREFS.ENTRANCE_KEY,
                        Constants.SHARED_PREFS.ENTRANCE_TIME_DEFAULT)
                .apply();
    }

    public Date getEntranceTime() {
        long savedEntranceTime = preferences.getLong(Constants.SHARED_PREFS.ENTRANCE_KEY,
                Constants.SHARED_PREFS.ENTRANCE_TIME_DEFAULT);
        return savedEntranceTime == -1 ? null : new Date(savedEntranceTime);

    }

    public void setFirstTimeInApp(boolean firstTimeInApp) {
        preferences.edit()
                .putBoolean(Constants.SHARED_PREFS.FIRST_TIME_IN_APP_KEY, firstTimeInApp)
                .apply();
    }

    public boolean isFirstTimeInApp() {
        return preferences.getBoolean (
                Constants.SHARED_PREFS.FIRST_TIME_IN_APP_KEY,
                Constants.SHARED_PREFS.FIRST_TIME_IN_APP_DEFAULT
        );
    }

    public void setMonitoringEnabled(boolean isMonitoringEnabled) {
        preferences.edit()
                .putBoolean(Constants.SHARED_PREFS.IS_MONITORING_ENABLED_KEY, isMonitoringEnabled)
                .apply();
    }

    public boolean isMonitoringEnabled() {
        return preferences.getBoolean (
                Constants.SHARED_PREFS.IS_MONITORING_ENABLED_KEY,
                Constants.SHARED_PREFS.IS_MONITORING_ENABLED_DEFAULT
        );
    }

}
