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


    String name="3 idiots";

    @Headers({
            "Accepts: application/json",
            "t: Batman",
            "apikey:8dfb77b4"
    })
    @GET("?t={movie_name}&apikey=8dfb77b4")
    Call<PostPojo> getBatman(
            @Path("movie_name") String movie_name);


    @GET("?t="+name+"&apikey=8dfb77b4")
    Call<PostPojo> getMovieDetails(@Query("Title") String Title);



}