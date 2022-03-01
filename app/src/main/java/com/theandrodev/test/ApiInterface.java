package com.theandrodev.test;


import static com.theandrodev.test.MainActivity.movie_name;
import static com.theandrodev.test.RetrofitInstance.APIKEY;
import static com.theandrodev.test.RetrofitInstance.BASEURL;

import android.icu.text.CaseMap;
import android.provider.SyncStateContract;

import java.util.List;
import java.util.ResourceBundle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {





        String name="";


    @GET("/")
    Call<PostPojo> getMovie(@Query("t") String movie_name,
                             @Query("apikey")String apikey,
                             @Query("type") String type);




}