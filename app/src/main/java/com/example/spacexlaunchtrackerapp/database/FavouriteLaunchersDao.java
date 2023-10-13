package com.example.spacexlaunchtrackerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouriteLaunchersDao {

    @Query("SELECT * FROM FavouriteDataEntity")
    LiveData<List<FavouriteDataEntity>> getAll();


    @Query("SELECT * FROM FavouriteDataEntity WHERE flightNumber = :flagNumber")
    LiveData<List<FavouriteDataEntity>> getlauncherData(int flagNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(FavouriteDataEntity favouriteDataEntity);

    @Delete
    void delete(FavouriteDataEntity favouriteDataEntity);

    @Query("DELETE FROM FavouriteDataEntity Where flightNumber = :flagNumber")
    void delete(int flagNumber);
}
