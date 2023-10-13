package com.example.spacexlaunchtrackerapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LauncherEntity {
    @PrimaryKey
    public int id;
    public String launcherJsonObject;


}
