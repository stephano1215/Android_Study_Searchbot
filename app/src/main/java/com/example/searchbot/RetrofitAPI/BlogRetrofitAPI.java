package com.example.searchbot.RetrofitAPI;

import com.example.searchbot.Classes.BlogData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BlogRetrofitAPI {

    @Headers({"X-Naver-Client-Id: DaDK7VBoX5_ToBAO7FR5", "X-Naver-Client-Secret: iCIPM8FD99"})
    @GET("v1/search/blog.json")
    Call<BlogData> getBlogList(@Query("query") String query, @Query("display")Integer display, @Query("start")Integer start, @Query("sort")String sort);
}
