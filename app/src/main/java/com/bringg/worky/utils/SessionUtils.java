package com.bringg.worky.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.bringg.worky.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mickael on 07/03/2018.
 */

public class SessionUtils {


    public static String parseDuration(Long duration)
    {
        if(duration == null)
            return "";

        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration))
        );
    }

    public static String parseDate(Context context, Date date)
    {

        if(date == null)
            return context.getString(R.string.invalid_date);

        DateFormat format = DateFormat.getDateTimeInstance();

        return format.format(date);
    }

}
