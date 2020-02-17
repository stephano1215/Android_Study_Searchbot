package com.example.searchbot.Pagination;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.example.searchbot.Classes.ImageData;
import com.example.searchbot.RetrofitAPI.ImageRetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemDataSource extends ItemKeyedDataSource<Integer, ImageData.items> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    String query;
    private Retrofit mRetrofit;
    private ImageRetrofitAPI mImageRetrofitAPI;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<ImageData.items> callback) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mImageRetrofitAPI = mRetrofit.create(ImageRetrofitAPI.class);
        mImageRetrofitAPI.getImageList(query, PAGE_SIZE, FIRST_PAGE, "sim", "all")
                .enqueue(new Callback<ImageData>() {
                    @Override
                    public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                        callback.onResult(response.body().items, FIRST_PAGE, FIRST_PAGE + PAGE_SIZE);
                    }

                    @Override
                    public void onFailure(Call<ImageData> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<ImageData.items> callback) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mImageRetrofitAPI = mRetrofit.create(ImageRetrofitAPI.class);
        mImageRetrofitAPI.getImageList(query, PAGE_SIZE, params.key, "sim", "all")
                .enqueue(new Callback<ImageData>() {
                    @Override
                    public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                        if (response.body() != null){
                            callback.onResult(response.body().items);
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageData> call, Throwable t) {

                    }
                });


    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<ImageData.items> callback) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://openapi.naver.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        mImageRetrofitAPI = mRetrofit.create(ImageRetrofitAPI.class);
        mImageRetrofitAPI.getImageList(query, PAGE_SIZE, params.key, "sim", "all")
                 .enqueue(new Callback<ImageData>() {
                     @Override
                     public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 20 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().items);
                        }
                     }

                     @Override
                     public void onFailure(Call<ImageData> call, Throwable t) {

                     }
                 });
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull ImageData.items item) {
        return null;
    }
}
