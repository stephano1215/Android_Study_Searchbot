package com.example.searchbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.searchbot.Adapters.DetailAdapter;
import com.example.searchbot.CustomData.ImageData;
import com.example.searchbot.RetrofitAPI.ImageRetrofitAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageDetailActivity extends AppCompatActivity {

    private TextView ImageDetailTitle;
    private RecyclerView DetailView;
    private DetailAdapter mDetailAdapter;
    private GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
    private ImageRetrofitAPI mImageRetrofitAPI;
    private ImageData IMGresult;
    private ArrayList<String> mURLThumbArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        init();
    }

    private void init() {
        RetrofitInit();
        ImageDetailTitle = findViewById(R.id.ImageDetailTitle);
        DetailView = findViewById(R.id.ImageDetailArea);

        final String query;

        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        ImageDetailTitle.setText("\t" + query);
        mDetailAdapter = new DetailAdapter();
        DetailView.setLayoutManager(gridLayoutManager);

        DetailView.setAdapter(mDetailAdapter);
        mDetailAdapter.setQuery(query);
        mDetailAdapter.setURLThumbArray(mURLThumbArray);

        callImageList(query, 1);
    }

    private void RetrofitInit() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mImageRetrofitAPI = mRetrofit.create(ImageRetrofitAPI.class);
    }

    private void callImageList(String data, int start) {
        Call<ImageData> mCallImageList = mImageRetrofitAPI.getImageList(data, 100, start, "sim", "all");
        mCallImageList.enqueue(mImageRetrofitCallback);
    }

    private Callback<ImageData> mImageRetrofitCallback = new Callback<ImageData>() {
        @Override
        public void onResponse(Call<ImageData> call, Response<ImageData> response) {
            IMGresult = response.body();
            for (int i = 0; i < IMGresult.items.size(); i++) {
                mURLThumbArray.add(IMGresult.items.get(i).thumbnail);
            }
            mDetailAdapter.notifyItemChanged(mDetailAdapter.getItemCount());
        }

        @Override
        public void onFailure(Call<ImageData> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
