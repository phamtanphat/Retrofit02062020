package com.example.retrofit02062020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
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
                

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://khoapham.vn/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client()
    }
}