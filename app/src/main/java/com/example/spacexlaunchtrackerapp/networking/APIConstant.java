package com.example.spacexlaunchtrackerapp.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

public  class APIConstant {
    public static  String API_BASE_PATH = "https://api.spacexdata.com/v3/";
    public static  String API_GET_LAUNCHERS = "launches";

    public static Boolean  isInternectConnnected(Context context)
    {
        Boolean result = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network networkCapabilities = connectivityManager.getActiveNetwork();
                NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(networkCapabilities);

                result = actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ? true : actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ? true : actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ? true : false;

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
