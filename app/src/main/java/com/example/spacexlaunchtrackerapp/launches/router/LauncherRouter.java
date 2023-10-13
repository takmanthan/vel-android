package com.example.spacexlaunchtrackerapp.launches.router;

import android.content.Context;
import android.content.Intent;

import com.example.spacexlaunchtrackerapp.launches.contractor.LauncherContractor;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.example.spacexlaunchtrackerapp.launches.view.LauncherDetailActivity;
import com.example.spacexlaunchtrackerapp.utility.BundleConstants;

public class LauncherRouter implements LauncherContractor.Router {

    private Context context;

    public LauncherRouter(Context context) {
        this.context = context;
    }

    @Override
    public void redirectToDetail(LaunchersResponse launchersResponse) {
        Intent intent = new Intent(context, LauncherDetailActivity.class);
        intent.putExtra(BundleConstants.LAUNCHER_DATA_BUNDLE, launchersResponse);
        context.startActivity(intent);
    }
}
