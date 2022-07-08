package com.radixweb.trendingrepo.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;

import java.util.List;

@Dao
public interface TrendRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepositories(List<TrendRepoEntity> githubEntities);

    @Query("SELECT * FROM `TrendRepoEntity` where page = :page")
    List<TrendRepoEntity> getRepositoriesByPage(Long page);

}
