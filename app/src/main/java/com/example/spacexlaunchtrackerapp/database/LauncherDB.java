package com.example.spacexlaunchtrackerapp.database;


import android.os.AsyncTask;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.spacexlaunchtrackerapp.BaseActivity;
import com.example.spacexlaunchtrackerapp.R;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LauncherDB {


    /**
     * Get Bookmark Data Entity
     * @param launchersResponse
     * @return
     */
    private static BookmarkDataEntity getBookmarkDataEntity(LaunchersResponse launchersResponse){
        BookmarkDataEntity bookmarkDataEntity = new BookmarkDataEntity();

        bookmarkDataEntity.createdDate = Calendar.getInstance().getTimeInMillis();
        bookmarkDataEntity.launcherJsonObject = new Gson().toJson(launchersResponse);
        bookmarkDataEntity.flightNumber = launchersResponse.flight_number;

        return bookmarkDataEntity;
    }

    /**
     * Get Favourite Data Entity
     * @param launchersResponse
     * @return
     */
    private static FavouriteDataEntity getFavouriteDataEntity(LaunchersResponse launchersResponse){
        FavouriteDataEntity favouriteDataEntity = new FavouriteDataEntity();

        favouriteDataEntity.createdDate = Calendar.getInstance().getTimeInMillis();
        favouriteDataEntity.launcherJsonObject = new Gson().toJson(launchersResponse);
        favouriteDataEntity.flightNumber = launchersResponse.flight_number;

        return favouriteDataEntity;
    }

    /**
     *  Insert and get data using Database Async way
     */
    public static void insertFavourite(BaseActivity mActivity, LaunchersResponse launchersResponse)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FavouriteLaunchersDao favouriteDao = mActivity.appDatabase.favouriteDao();
                    favouriteDao.insertData(getFavouriteDataEntity(launchersResponse));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }


    /**
     *  Delete data using Database Async way
     */
    public static void deleteFavourite(BaseActivity mActivity, LaunchersResponse launchersResponse)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FavouriteLaunchersDao favouriteDao = mActivity.appDatabase.favouriteDao();
                    favouriteDao.delete(launchersResponse.flight_number);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *  Get data using Database Async way
     */
    public static LiveData<List<FavouriteDataEntity>> getFavourite(BaseActivity mActivity, LaunchersResponse launchersResponse)
    {
        FavouriteLaunchersDao favouriteDao = mActivity.appDatabase.favouriteDao();
        return  favouriteDao.getlauncherData(launchersResponse.flight_number);
    }



    /**
     *  Insert and get data using Database Async way
     */
    public static void insertBookmark(BaseActivity mActivity, LaunchersResponse launchersResponse)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    BookmarkDao bookmarkDao = mActivity.appDatabase.bookmarkDao();
                    bookmarkDao.insertData(getBookmarkDataEntity(launchersResponse));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }


    /**
     *  Delete data using Database Async way
     */
    public static void deleteBookmark(BaseActivity mActivity, LaunchersResponse launchersResponse)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    BookmarkDao bookmarkDao = mActivity.appDatabase.bookmarkDao();
                    bookmarkDao.delete(launchersResponse.flight_number);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *  Get data using Database Async way
     */
    public static LiveData<List<BookmarkDataEntity>> getBookmark(BaseActivity mActivity, LaunchersResponse launchersResponse)
    {
        BookmarkDao bookmarkDao = mActivity.appDatabase.bookmarkDao();
        return  bookmarkDao.getlauncherData(launchersResponse.flight_number);
    }


    /**
     *  Insert and get data using Database Async way
     */
    public static void insertLauncher(BaseActivity mActivity, ArrayList<LaunchersResponse> launchersResponse)
    {

        try {
            LiveData<List<LauncherEntity>> liveData = getLaunchers(mActivity);
            liveData.observe(mActivity, new Observer<List<LauncherEntity>>() {
                @Override
                public void onChanged(List<LauncherEntity> launcherDataEntities) {
                    if (launcherDataEntities.size() > 0)
                    {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mActivity.appDatabase.launcherDao().updateData(1, new Gson().toJson(launchersResponse));
                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    LauncherEntity launcherEntity = new LauncherEntity();
                                    launcherEntity.id = 1;
                                    launcherEntity.launcherJsonObject = new Gson().toJson(launchersResponse);
                                    mActivity.appDatabase.launcherDao().insertData(launcherEntity);
                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     *  Get data using Database Async way
     */
    public static LiveData<List<LauncherEntity>> getLaunchers(BaseActivity mActivity)
    {
        LauncherDao transactionDao = mActivity.appDatabase.launcherDao();
        return  transactionDao.getAll();
    }
}
