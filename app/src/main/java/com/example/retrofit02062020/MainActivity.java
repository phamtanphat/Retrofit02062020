package com.example.retrofit02062020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Buoc 1 : Khoi tao retrofit

        // Gson
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        // Okhttp Client
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .readTimeout(30 , TimeUnit.SECONDS)
                                .writeTimeout(30 , TimeUnit.SECONDS)
                                .connectTimeout(30 , TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                .build();



        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://khoapham.vn/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(okHttpClient)
                            .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);

        Call<Demo1> callbackDemo1 = apiRequest.getDataDemo1();

        callbackDemo1.enqueue(new Callback<Demo1>() {
            @Override
            public void onResponse(Call<Demo1> call, Response<Demo1> response) {
                Demo1 demo1 = response.body();
                Log.d("BBB",demo1.toString());
            }

            @Override
            public void onFailure(Call<Demo1> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });

    }
}