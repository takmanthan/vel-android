package com.example.spacexlaunchtrackerapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookmarkDataEntity {
    @PrimaryKey
    public Long createdDate;
    public String launcherJsonObject;
    public int flightNumber;
}
