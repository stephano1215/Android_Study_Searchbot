package com.example.searchbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.searchbot.Adapters.RealDetailAdapter;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private RealDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        ArrayList<String> URLArray = intent.getStringArrayListExtra("src");
        int position = intent.getIntExtra("position", 0);
        TextView textView = findViewById(R.id.ViewDetailTitle);
        textView.setText("\t" + query);
        mViewPager = findViewById(R.id.ViewDetailView);

        mAdapter = new RealDetailAdapter();
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(mAdapter);
        mAdapter.setURLArray(URLArray);
        mAdapter.notifyDataSetChanged();
        mViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mViewPager.setCurrentItem(position);

    }
}
