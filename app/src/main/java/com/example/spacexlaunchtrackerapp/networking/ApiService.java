package com.example.spacexlaunchtrackerapp.networking;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ApiService {

    public static Retrofit.Builder ApiCall() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(APIConstant.API_BASE_PATH)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(APIClient.getGsonConverter())
                .client(APIClient.getClient())
                .build()
                .create(ApiInterface.class);
        return builder;
    }
}
