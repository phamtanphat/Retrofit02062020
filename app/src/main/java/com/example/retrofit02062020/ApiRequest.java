package com.example.retrofit02062020;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    @GET("KhoaPhamTraining/json/tien/demo1.json")
    Call<Demo1> getDataDemo1();

    @GET("KhoaPhamTraining/json/tien/demo2.json")
    Call<Demo2> getDataDemo2();
}
