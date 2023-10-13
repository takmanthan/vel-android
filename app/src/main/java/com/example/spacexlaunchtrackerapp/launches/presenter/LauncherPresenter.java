package com.example.spacexlaunchtrackerapp.launches.presenter;

import android.content.Context;

import com.example.spacexlaunchtrackerapp.BaseActivity;
import com.example.spacexlaunchtrackerapp.launches.contractor.LauncherContractor;
import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.example.spacexlaunchtrackerapp.launches.intractor.LauncherIntractor;
import com.example.spacexlaunchtrackerapp.launches.router.LauncherRouter;

import java.util.ArrayList;

public class LauncherPresenter implements LauncherContractor.Presenter, LauncherContractor.IntractorOutput {

    private LauncherContractor.View view;
    private LauncherContractor.Intractor intractor;
    private LauncherContractor.Router router;

    public LauncherPresenter(LauncherContractor.View view, BaseActivity context) {
        this.view = view;
        intractor = new LauncherIntractor(this, context);
        router = new LauncherRouter(context);
    }

    @Override
    public void getAllLauncher() {
        intractor.callLaunchersAPI();
    }

    @Override
    public void onDestroy() {
        intractor = null;
        router = null;
    }

    @Override
    public void onLauncherClick(LaunchersResponse launchersResponse) {
        router.redirectToDetail(launchersResponse);
    }

    @Override
    public void onLauncherOutput(ArrayList<LaunchersResponse> launchersResponses) {
        view.setLaunchers(launchersResponses);
    }

    @Override
    public void showLoader() {
        view.showLoader();
    }

    @Override
    public void hideLoader() {
        view.hideLoader();
    }
}
