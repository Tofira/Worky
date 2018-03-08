package com.bringg.worky;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Mickael on 08/03/2018.
 */

public class WorkyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG)
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {

                }
            });
    }

}
