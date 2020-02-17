package com.example.searchbot.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.searchbot.R;
import com.example.searchbot.ViewActivity;
import com.example.searchbot.ViewHolders.DetailViewHolder;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {

    private ArrayList<String> URLThumbArray;
    private String query;

    public void setURLThumbArray(ArrayList<String> urlArray) {
        this.URLThumbArray = urlArray;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_detail, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(URLThumbArray.get(position)).placeholder(R.drawable.ic_launcher_background)
                .into(holder.detail);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("query", query);
                intent.putStringArrayListExtra("src", URLThumbArray);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (URLThumbArray != null) {
            return URLThumbArray.size();
        } else {
            return 0;
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
