package com.example.bohdan.provectustask;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bohdan on 30.06.2017.
 */

public class App extends Application {

    private static RandomuserApi mRandomuserApi;
    private Retrofit retrofit;

    public static RandomuserApi getmRandomuserApi() {
        return mRandomuserApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mRandomuserApi = retrofit.create(RandomuserApi.class);
    }
}
