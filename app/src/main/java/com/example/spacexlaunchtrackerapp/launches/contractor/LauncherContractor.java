package com.example.spacexlaunchtrackerapp.launches.contractor;

import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;

import java.util.ArrayList;


/**
 * Contractor Class to manage VIPER Architecture
 *
 *  * V = View
 *  * I = Intractor
 *  * P = Presenter
 *  * E = Entity
 *  * R = Router
 */
public class LauncherContractor {

    public interface View{
        void setLaunchers(ArrayList<LaunchersResponse> launchersResponses);
        void showLoader();
        void hideLoader();
    }

    public interface Intractor{
        void  callLaunchersAPI();
    }

    public interface Presenter{
        void getAllLauncher();
        void onDestroy();
        void onLauncherClick(LaunchersResponse launchersResponse);
    }

    public interface Router{
        void redirectToDetail(LaunchersResponse launchersResponse);

    }

    public interface IntractorOutput{
        void onLauncherOutput(ArrayList<LaunchersResponse> launchersResponses);
        void showLoader();
        void hideLoader();
    }
}
