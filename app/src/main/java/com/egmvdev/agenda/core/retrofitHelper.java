package com.egmvdev.agenda.core;

import com.egmvdev.agenda.gson.GsonConverterFactory;

import retrofit2.Retrofit;

public class retrofitHelper {
    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.devops.cycmovil.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
