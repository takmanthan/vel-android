package com.example.spacexlaunchtrackerapp.networking;

import com.google.gson.GsonBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import kotlin.jvm.Throws;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static OkHttpClient mClient;
    private static GsonConverterFactory mGsonConverter;

    /**
     * Don't forget to remove Interceptors (or change Logging Level to NONE)
     * in production! Otherwise people will be able to see your request and response on Log Cat.
     */
    public static OkHttpClient getClient() {
        if (mClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            httpBuilder.connectTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(30, TimeUnit.MINUTES)
                    .addInterceptor(interceptor);  /// show all JSON in logCat
            mClient = httpBuilder.build();
        }
        return mClient;
    }

    /**
     * Gson Converter for Gson client
     */
    public static GsonConverterFactory getGsonConverter() {
        if(mGsonConverter == null){
            mGsonConverter = GsonConverterFactory
                    .create(
                            new GsonBuilder()
                                    .setLenient()
                                    .disableHtmlEscaping()
                                    .create());
        }
        return mGsonConverter;
    }
}
