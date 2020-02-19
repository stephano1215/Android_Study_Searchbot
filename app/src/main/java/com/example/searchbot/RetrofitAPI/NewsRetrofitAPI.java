package com.example.searchbot.RetrofitAPI;

import com.example.searchbot.CustomData.NewsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NewsRetrofitAPI {

    @Headers({"X-Naver-Client-Id: DaDK7VBoX5_ToBAO7FR5", "X-Naver-Client-Secret: iCIPM8FD99"})
    @GET("v1/search/news.json")
    Call<NewsData> getNewsList(@Query("query") String query, @Query("display") Integer display, @Query("start") Integer start, @Query("sort") String sort);
}
