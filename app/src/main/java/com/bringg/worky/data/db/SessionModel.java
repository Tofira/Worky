package com.bringg.worky.data.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by Mickael on 08/03/2018.
 */
@Entity
public class SessionModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Nullable
    private Boolean isException;

    @TypeConverters(DateConverter.class)
    @Nullable
    private Date entranceTime;

    @TypeConverters(DateConverter.class)
    @Nullable
    private Date exitTime;

    @Nullable
    private Long duration;

    @Ignore
    public SessionModel(@Nullable Date entranceTime) {
        this(entranceTime, null, null, false);
    }

    public SessionModel(@Nullable Date entranceTime, @Nullable Date exitTime,
                        @Nullable Long duration, @NonNull Boolean isException) {
        this.entranceTime = entranceTime;
        this.exitTime = exitTime;
        this.isException = isException;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public Boolean isException() {
        return isException;
    }

    public Date getEntranceTime() {
        return entranceTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public Long getDuration() {
        return duration;
    }

}
