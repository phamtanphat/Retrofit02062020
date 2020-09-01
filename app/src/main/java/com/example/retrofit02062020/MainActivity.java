package com.example.retrofit02062020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Buoc 1 : Khoi tao retrofit

        sharedPreferences = getSharedPreferences("Token",MODE_PRIVATE);
        token = sharedPreferences.getString("tokenapp","");
        // Gson
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        // Okhttp Client
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                .readTimeout(30 , TimeUnit.SECONDS)
                                .writeTimeout(30 , TimeUnit.SECONDS)
                                .connectTimeout(30 , TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                                .addInterceptor(new Interceptor() {
                                    @Override
                                    public okhttp3.Response intercept(Chain chain) throws IOException {
                                        Request request = chain.request();
                                        if (!token.isEmpty()){
                                            request = request
                                                    .newBuilder()
                                                    .addHeader("Authorization","Bearer " + token).build();

                                        }
                                        return chain.proceed(request);
                                    }
                                })
                                .build();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://54.151.199.176/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(okHttpClient)
                            .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);

        Call<Object> callBackTour = apiRequest.getTour();
        callBackTour.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object object = response.body();
                Log.d("BBB",object.toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });


    }
    private void getDataDemo1(ApiRequest apiRequest){
        Call<Demo1> callbackDemo1 = apiRequest.getDataDemo1();

        callbackDemo1.enqueue(new Callback<Demo1>() {
            @Override
            public void onResponse(Call<Demo1> call, Response<Demo1> response) {
                Demo1 demo1 = response.body();
                Log.d("BBB",demo1.toString() + " 1");
            }

            @Override
            public void onFailure(Call<Demo1> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });
    }
    private void getDataDemo2(ApiRequest apiRequest){
        Call<Demo2> callbackDemo2 = apiRequest.getDataDemo2();

        callbackDemo2.enqueue(new Callback<Demo2>() {
            @Override
            public void onResponse(Call<Demo2> call, Response<Demo2> response) {
                Demo2 demo2 = response.body();
                Log.d("BBB",demo2.toString());
            }

            @Override
            public void onFailure(Call<Demo2> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });
    }
    private void login(ApiRequest apiRequest){
        Call<ResponseApi> callbackLogin = apiRequest.login("admin@boldwolf.com","L6mSCFub72LX26cT");
        callbackLogin.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                ResponseApi responseApi = response.body();
                if (responseApi != null){
                    editor = sharedPreferences.edit();
                    editor.putString("tokenapp",responseApi.getData().getToken());
                    editor.apply();
                }
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {

            }
        });
    }
}