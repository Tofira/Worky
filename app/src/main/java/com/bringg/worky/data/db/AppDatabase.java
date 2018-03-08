package com.bringg.worky.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bringg.worky.utils.Constants;
import com.bringg.worky.utils.SharedPrefUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mickael on 08/03/2018.
 */
@Database(entities = {SessionModel.class}, version = 1, exportSchema  = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getDatabase(Context context) {

        synchronized (AppDatabase.class) {
            if (instance == null) {
                instance =
                        Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DATABASE.DB_NAME)
                                .allowMainThreadQueries()
                                .build();
            }
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract SessionModelDao sessionsModel();

    public static boolean fillLastSession(Context context)
    {

        AppDatabase appDatabase = AppDatabase.getDatabase(context);

        Date currentDate = Calendar.getInstance().getTime();

        Date entranceTime = SharedPrefUtils.getInstance(context).getEntranceTime();

        // entranceTime = null -> We received an exit event without a prior
        // entering event -> add an exception row
        if(entranceTime == null)
        {

            addExceptionSession(context, false);

            return false;

        }


        //Add the session. If the duration is shorter than the minimal
        // session time ->add it with an exception
        long sessionDuration = currentDate.getTime() - entranceTime.getTime();

        SessionModel sessionModel = new SessionModel(
                entranceTime,
                currentDate,
                sessionDuration,
                sessionDuration < Constants.SESSION.MINIMAL_SESSION_TIME
        );

        appDatabase.sessionsModel().addSession(sessionModel);

        SharedPrefUtils.getInstance(context).clearEntranceTime();

        return true;


    }


    public static void addExceptionSession(Context context, boolean isStartDate)
    {
        AppDatabase appDatabase = AppDatabase.getDatabase(context);

        Date currentDate = Calendar.getInstance().getTime();

        SessionModel sessionModel = new SessionModel(
                isStartDate ? currentDate: null,
                !isStartDate ? currentDate: null,
                null,
                true
        );

        appDatabase.sessionsModel().addSession(sessionModel);

        SharedPrefUtils.getInstance(context).clearEntranceTime();
    }

}
