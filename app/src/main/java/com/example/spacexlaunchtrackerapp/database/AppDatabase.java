package com.example.spacexlaunchtrackerapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavouriteDataEntity.class, BookmarkDataEntity.class, LauncherEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavouriteLaunchersDao favouriteDao();
    public abstract BookmarkDao bookmarkDao();
    public abstract LauncherDao launcherDao();
}
