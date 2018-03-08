package com.bringg.worky.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Mickael on 08/03/2018.
 */
@Dao
@TypeConverters(DateConverter.class)
public interface SessionModelDao {

    @Query("select * from SessionModel ORDER BY id DESC")
    LiveData<List<SessionModel>> getAllSessions();

    @Insert(onConflict = REPLACE)
    void addSession(SessionModel sessionModel);

    @Update
    int updateSession(SessionModel sessionModel );

    @Query("select * from SessionModel ORDER BY id DESC LIMIT 1")
    SessionModel getLastSession();

}
