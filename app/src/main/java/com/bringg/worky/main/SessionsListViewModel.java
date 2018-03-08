package com.bringg.worky.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.bringg.worky.data.db.AppDatabase;
import com.bringg.worky.data.db.SessionModel;
import java.util.List;


public class SessionsListViewModel extends AndroidViewModel {

    private final LiveData<List<SessionModel>> sessionsList;

    private AppDatabase appDatabase;

    public SessionsListViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        sessionsList = appDatabase.sessionsModel().getAllSessions();

    }

    public LiveData<List<SessionModel>> getSessionsList() {
        return sessionsList;
    }


}
