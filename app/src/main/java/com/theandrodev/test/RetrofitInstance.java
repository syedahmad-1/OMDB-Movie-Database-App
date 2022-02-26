package com.theandrodev.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    static Retrofit retrofit;
    private static Gson gson;
    public static final String BASEURL="http://www.omdbapi.com/";
    public static final String APIKEY ="8dfb77b4" ;

    //https://www.omdbapi.com/?t=Batman&apikey=APIKEY

    public static Retrofit getRetrofit() {

        if(retrofit==null)
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
            ApiInterface apiInterface =retrofit.create(ApiInterface.class);
        }

        return retrofit;
    }
}