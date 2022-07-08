package com.radixweb.trendingrepo.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.radixweb.trendingrepo.data.local.converter.TrendRepoConverter;
import com.radixweb.trendingrepo.data.local.dao.TrendRepoDao;
import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;

@Database(entities = {TrendRepoEntity.class}, version = 1,  exportSchema = false)
@TypeConverters({TrendRepoConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract TrendRepoDao githubDao();
}
