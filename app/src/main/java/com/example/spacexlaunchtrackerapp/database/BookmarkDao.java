package com.example.spacexlaunchtrackerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookmarkDao {
    @Query("SELECT * FROM bookmarkdataentity")
    LiveData<List<BookmarkDataEntity>> getAll();


    @Query("SELECT * FROM bookmarkdataentity WHERE flightNumber = :flagNumber")
    LiveData<List<BookmarkDataEntity>> getlauncherData(int flagNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(BookmarkDataEntity bookmarkDataEntity);

    @Delete
    void delete(BookmarkDataEntity bookmarkDataEntity);

    @Query("DELETE FROM bookmarkdataentity Where flightNumber = :flagNumber")
    void delete(int flagNumber);
}
