package com.example.spacexlaunchtrackerapp.networking;

import com.example.spacexlaunchtrackerapp.launches.entity.LaunchersResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET()
    Call<ArrayList<LaunchersResponse>> getApi(@Url String apiName);

}
