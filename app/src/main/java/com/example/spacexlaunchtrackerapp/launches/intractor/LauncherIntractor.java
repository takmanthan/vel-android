package com.example.spacexlaunchtrackerapp.launches.intractor;

import static com.example.spacexlaunchtrackerapp.database.LauncherDB.getBookmark;
import static com.example.spacexlaunchtrackerapp.database.LauncherDB.getLaunchers;
import static com.example.spacexlaunchtrackerapp.networking.ApiService.ApiCall;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.spacexlaunchtrackerapp.BaseActivity;
import com.example.spacexlaunchtrackerapp.R;
import com.example.spacexlaunchtrackerapp.database.BookmarkDataEntity;
import com.example.spacexlaunchtrackerapp.database.LauncherDB;
import com.example.spacexlaunchtrackerapp.database.LauncherEntity;
import com.example.spacexlaunchtrackerapp.launches.contractor.LauncherContractor;
import com.example.spacexlaunchtrackerapp.launches.entity.LauncherResponseData;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.example.spacexlaunchtrackerapp.networking.APIConstant;
import com.example.spacexlaunchtrackerapp.networking.ApiInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LauncherIntractor implements LauncherContractor.Intractor {

    private LauncherContractor.IntractorOutput intractorOutput;
    private BaseActivity mActivity;

    public LauncherIntractor(LauncherContractor.IntractorOutput intractorOutput, BaseActivity mActivity) {
        this.intractorOutput = intractorOutput;
        this.mActivity = mActivity;
    }

    @Override
    public void callLaunchersAPI() {
        if (APIConstant.isInternectConnnected(mActivity)) {
            intractorOutput.showLoader();
            final Call<ArrayList<LaunchersResponse>> existResponseCall= ApiCall().build().create(ApiInterface.class).getApi(APIConstant.API_GET_LAUNCHERS);
            existResponseCall.enqueue(new Callback<ArrayList<LaunchersResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<LaunchersResponse>> call, retrofit2.Response<ArrayList<LaunchersResponse>> response) {
                    try {
                        if (response.code() == 200)
                        {

                            //Insert Data In Database for Offline
                            LauncherDB.insertLauncher(mActivity, response.body());

                            //transfer data to Presenter for showing data
                            intractorOutput.onLauncherOutput(response.body());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        intractorOutput.hideLoader();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<LaunchersResponse>> call, Throwable throwable) {
                    Log.e("API Failure", throwable.getLocalizedMessage());
                    intractorOutput.hideLoader();
                }
            });
        }else {
            try {
                LiveData<List<LauncherEntity>> liveData = getLaunchers(mActivity);
                liveData.observe(mActivity, new Observer<List<LauncherEntity>>() {
                    @Override
                    public void onChanged(List<LauncherEntity> launcherDataEntities) {
                        ArrayList<LaunchersResponse> launchersResponses = new Gson().fromJson(launcherDataEntities.get(0).launcherJsonObject, LauncherResponseData.class);
                        intractorOutput.onLauncherOutput(launchersResponses);
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                intractorOutput.hideLoader();
            }

        }
    }
}
