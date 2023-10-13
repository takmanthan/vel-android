package com.example.spacexlaunchtrackerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LauncherDao {

    @Query("SELECT * FROM launcherentity")
    LiveData<List<LauncherEntity>> getAll();

    @Query("UPDATE launcherentity SET launcherJsonObject= :bookmarkDataEntity WHERE id = :id")
    void updateData(int id, String bookmarkDataEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(LauncherEntity bookmarkDataEntity);
}
