package com.example.searchbot;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchbot.Adapters.MessageAdapter;
import com.example.searchbot.CustomData.BlogData;
import com.example.searchbot.CustomData.ImageData;
import com.example.searchbot.CustomData.LoginData;
import com.example.searchbot.CustomData.NewsData;
import com.example.searchbot.CustomData.source;
import com.example.searchbot.RetrofitAPI.BlogRetrofitAPI;
import com.example.searchbot.RetrofitAPI.ImageRetrofitAPI;
import com.example.searchbot.RetrofitAPI.LoginRetrofitAPI;
import com.example.searchbot.RetrofitAPI.NewsRetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity {

    private static final int TYPE_REQUEST = 0;
    private static final int TYPE_TEXT = 1;
    private static final int TYPE_IMG = 2;
    private static final int TYPE_BLOG = 3;
    private static final int TYPE_NEWS = 4;

    private RecyclerView mRecyclerview; //drag&drop helper
    private MessageAdapter mMessageAdapter;
    private EditText mQuery;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private ArrayList<source> mTypeArray = new ArrayList<>();
    private BlogRetrofitAPI mBlogRetrofitAPI;
    private NewsRetrofitAPI mNewsRetrofitAPI;
    private ImageRetrofitAPI mImageRetrofitAPI;
    private LoginRetrofitAPI mLoginRetrofitAPI;
    private CheckBox mBlogCheck;
    private CheckBox mNewsCheck;
    private String query;
    private String Token;
    private String User_name;
    private String User_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    private void init() {
        RetrofitInit();
        Intent intent = getIntent();
        Token = intent.getStringExtra("Token");
        callLoginList("Bearer " + Token);
        mRecyclerview = findViewById(R.id.ChatArea);
        mMessageAdapter = new MessageAdapter();
        mQuery = findViewById(R.id.ChatContent);
        ImageButton mSearchButton = findViewById(R.id.ChatBtnSearch);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.setAdapter(mMessageAdapter);
        mSearchButton.setOnClickListener(onClickListener);
        mBlogCheck = findViewById(R.id.blogcheck);
        mNewsCheck = findViewById(R.id.newscheck);
        mMessageAdapter.setTypeArray(mTypeArray);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ChatBtnSearch) {
                if (mQuery.getText().length() == 0) {
                    Toast.makeText(ChatActivity.this, "검색어를 입력하세요!", Toast.LENGTH_SHORT).show();
                } else {
                    query = mQuery.getText().toString();
                    mQuery.setText("");
                    mTypeArray.add(new source(TYPE_REQUEST, query, User_name, User_profile));
                    mMessageAdapter.notifyItemInserted(mMessageAdapter.getItemCount());
                    mRecyclerview.getLayoutManager().scrollToPosition(mMessageAdapter.getItemCount() - 1);
                    if (mBlogCheck.isChecked() && mNewsCheck.isChecked()) {
                        callImageList(query);
                        callBlogList(query);
                        callNewsList(query);
                    } else if (mNewsCheck.isChecked()) {
                        callImageList(query);
                        callNewsList(query);
                    } else if (mBlogCheck.isChecked()) {
                        callImageList(query);
                        callBlogList(query);
                    } else {
                        callImageList(query);
                    }
                }
            }
        }
    };

    private void RetrofitInit() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBlogRetrofitAPI = mRetrofit.create(BlogRetrofitAPI.class);
        mNewsRetrofitAPI = mRetrofit.create(NewsRetrofitAPI.class);
        mImageRetrofitAPI = mRetrofit.create(ImageRetrofitAPI.class);
        mLoginRetrofitAPI = mRetrofit.create(LoginRetrofitAPI.class);
    }

    private void callBlogList(String data) {
        Call<BlogData> mCallBlogList = mBlogRetrofitAPI.getBlogList(data, 10, 1, "sim");
        mCallBlogList.enqueue(mBlogRetrofitCallback);
    }

    private void callNewsList(String data) {
        Call<NewsData> mCallNewsList = mNewsRetrofitAPI.getNewsList(data, 10, 1, "sim");
        mCallNewsList.enqueue(mNewsRetrofitCallback);
    }

    private void callImageList(String data) {
        Call<ImageData> mCallImageList = mImageRetrofitAPI.getImageList(data, 10, 1, "sim", "all");
        mCallImageList.enqueue(mImageRetrofitCallback);
    }

    private void callLoginList(String data) {
        Call<LoginData> mCallLoginList = mLoginRetrofitAPI.getLoginList(data);
        mCallLoginList.enqueue(mLoginRetrofitCallback);
    }

    private Callback<LoginData> mLoginRetrofitCallback = new Callback<LoginData>() {
        @Override
        public void onResponse(Call<LoginData> call, Response<LoginData> response) {
            LoginData result = response.body();
            User_name = result.items.name;
            User_profile = result.items.profile_image;
        }

        @Override
        public void onFailure(Call<LoginData> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private Callback<ImageData> mImageRetrofitCallback = new Callback<ImageData>() {
        @Override
        public void onResponse(Call<ImageData> call, Response<ImageData> response) {
            ImageData IMGresult = response.body();
            if (IMGresult.items.size() == 0) {
                mTypeArray.add(new source(TYPE_TEXT, "이미지 검색 결과가 없습니다.", null, null));
                mMessageAdapter.notifyItemInserted(mMessageAdapter.getItemCount());
            } else {
                mTypeArray.add(new source(TYPE_TEXT, query + "에 대한 이미지 검색 결과입니다.", null, null));
                List<ImageData.items> imageItems = IMGresult.items;
                int random = (int) (Math.random() * imageItems.size());
                String Imagesrc1 = imageItems.get(random).thumbnail;
                random = (int) (Math.random() * IMGresult.items.size());
                String Imagesrc2 = imageItems.get(random).thumbnail;
                mTypeArray.add(new source(TYPE_IMG, query, Imagesrc1, Imagesrc2));
                mMessageAdapter.notifyItemInserted(mMessageAdapter.getItemCount());
                mRecyclerview.getLayoutManager().scrollToPosition(mMessageAdapter.getItemCount() - 1);
            }
        }

        @Override
        public void onFailure(Call<ImageData> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private Callback<BlogData> mBlogRetrofitCallback = new Callback<BlogData>() {
        @Override
        public void onResponse(Call<BlogData> call, Response<BlogData> response) {
            BlogData BLOGresult = response.body();
            if (BLOGresult.items.size() == 0) {
                mTypeArray.add(new source(TYPE_TEXT, "블로그 검색 결과가 없습니다.", null, null));
                mMessageAdapter.notifyItemInserted(mMessageAdapter.getItemCount());
            } else {
                mTypeArray.add(new source(TYPE_TEXT, query + "에 대한 블로그 검색 결과입니다.", null, null));
                int random = (int) (Math.random() * BLOGresult.items.size());
                Spanned temp = Html.fromHtml(BLOGresult.items.get(random).title);
                String mBlogTitle = temp.toString();
                temp = Html.fromHtml(BLOGresult.items.get(random).description);
                String mBlogContent = temp.toString();
                String mBlogLink = BLOGresult.items.get(random).link;
                mTypeArray.add(new source(TYPE_BLOG, mBlogTitle, mBlogContent, mBlogLink));
                mMessageAdapter.notifyItemInserted(mMessageAdapter.getItemCount());
                mRecyclerview.getLayoutManager().scrollToPosition(mMessageAdapter.getItemCount() - 1);
            }
        }

        @Override
        public void onFailure(Call<BlogData> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private Callback<NewsData> mNewsRetrofitCallback = new Callback<NewsData>() {
        @Override
        public void onResponse(Call<NewsData> call, Response<NewsData> response) {
            NewsData NEWSresult = response.body();
            if (NEWSresult.items.size() == 0) {
                mTypeArray.add(new source(TYPE_TEXT, "뉴스 검색 결과가 없습니다.", null, null));
                mMessageAdapter.notifyItemInserted(mMessageAdapter.getItemCount());
            } else {
                mTypeArray.add(new source(TYPE_TEXT, query + "에 대한 뉴스 검색 결과입니다.", null, null));
                int random = (int) (Math.random() * NEWSresult.items.size());
                Spanned temp = Html.fromHtml(NEWSresult.items.get(random).title);
                String mNewsTitle = temp.toString();
                temp = Html.fromHtml(NEWSresult.items.get(random).description);
                String mNewsContent = temp.toString();
                String mNewsLink = NEWSresult.items.get(random).link;
                mTypeArray.add(new source(TYPE_NEWS, mNewsTitle, mNewsContent, mNewsLink));
                mMessageAdapter.notifyItemInserted(mMessageAdapter.getItemCount());
                mRecyclerview.getLayoutManager().scrollToPosition(mMessageAdapter.getItemCount() - 1);
            }
        }

        @Override
        public void onFailure(Call<NewsData> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
