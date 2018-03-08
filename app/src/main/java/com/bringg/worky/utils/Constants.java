package com.bringg.worky.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mickael on 3/6/2018.
 */

public class Constants {

    public static class NotificationsConstants
    {
        public static final String NOTIFICATION_CHANNEL_ID = "WORKY_NOTIFICATION";
        public static final int NOTIFICATION_ID = 1579;
    }

    public static class GeofencesConstants
    {
        public static final int GEOFENCE_RADIUS = 200;
        public static final String GEOFENCE_REQUEST_ID = "WORKY_GEO";
    }

    public static class LocationServicesConstants
    {
        public static final long LOCATION_INTERVAL =  TimeUnit.MINUTES.toMillis(2);
        public static final long LOCATION_FASTEST_INTERVAL =  TimeUnit.MINUTES.toMillis(1);
    }

    public static class DATABASE
    {
        public static final String DB_NAME = "worky_db";
    }

    public static class SESSION
    {
        public static final long MINIMAL_SESSION_TIME = TimeUnit.HOURS.toMillis(4);;
    }

    public static class SHARED_PREFS
    {
        public static final String ENTRANCE_KEY = "ENTRANCE_KEY";
        public static final long ENTRANCE_TIME_DEFAULT = -1;

        public static final String LOCATION_LAT_KEY = "LOCATION_LAT_KEY";
        public static final long LOCATION_LAT_DEFAULT = -1;

        public static final String LOCATION_LONGITUDE_KEY = "LOCATION_LONGITUDE_KEY";
        public static final long LOCATION_LONGITUDE_DEFAULT = -1;

        public static final String FIRST_TIME_IN_APP_KEY = "FIRST_TIME_IN_APP_KEY";
        public static final boolean FIRST_TIME_IN_APP_DEFAULT = true;

        public static final String IS_MONITORING_ENABLED_KEY = "IS_MONITORING_ENABLED_KEY";
        public static final boolean IS_MONITORING_ENABLED_DEFAULT = true;

    }

    public static class ANIMATIONS
    {
        public static final int SPLASH_ANIMATION_ITEM_DELAY = 300;
        public static final int SPLASH_ANIMATION_ITEM_DURATION = 800;
        public static final int SPLASH_ANIMATION_INITIAL_DELAY = 1200;
    }


}
