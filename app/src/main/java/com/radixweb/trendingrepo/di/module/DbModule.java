package com.radixweb.trendingrepo.di.module;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.radixweb.trendingrepo.data.local.AppDatabase;
import com.radixweb.trendingrepo.data.local.dao.TrendRepoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class, "Github.db")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    TrendRepoDao provideGithubDao(@NonNull AppDatabase appDatabase) {
        return appDatabase.githubDao();
    }
}
