package com.radixweb.trendingrepo.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.radixweb.trendingrepo.data.local.entity.TrendRepoEntity;

import java.util.List;

@Dao
public interface TrendRepoDao {

    /**
     * @param githubEntities list of model entities to the DB
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepositories(List<TrendRepoEntity> githubEntities);

    /**
     * @param page fire query according to page field.
     * @return get the data using the page field
     */
    @Query("SELECT * FROM `TrendRepoEntity` where page = :page")
    List<TrendRepoEntity> getRepositoriesByPage(Long page);

}
