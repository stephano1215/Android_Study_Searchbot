package com.example.searchbot.RetrofitAPI;

import com.example.searchbot.CustomData.LoginData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface LoginRetrofitAPI {

    @Headers("Content-Type: application/json")
    @GET("v1/nid/me")
    Call<LoginData> getLoginList(@Header("Authorization") String Token);
}
