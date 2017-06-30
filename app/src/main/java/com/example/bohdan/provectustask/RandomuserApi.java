package com.example.bohdan.provectustask;

import com.example.bohdan.provectustask.data.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bohdan on 30.06.2017.
 */

public interface RandomuserApi {
    @GET("/api/")
    Call<Result> getData(@Query("format") String format);

    @GET("/api/")
    Call<Result> getUsers(@Query("results") String size);
}

