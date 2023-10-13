package com.example.spacexlaunchtrackerapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.spacexlaunchtrackerapp.database.AppDatabase;

/**
 * We are using VIPER Architecture as clear Architecture
 * V = View
 * I = Intractor
 * P = Presenter
 * E = Entity
 * R = Router
 *
 * Above interfaces are used in this Architecture to manage the code
 */
public class BaseActivity extends AppCompatActivity {

    public AppDatabase appDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "launcher_db").fallbackToDestructiveMigration().build();
    }
}
