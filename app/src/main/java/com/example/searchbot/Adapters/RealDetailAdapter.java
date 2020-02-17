package com.example.searchbot.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.searchbot.R;
import com.example.searchbot.ViewHolders.ViewViewHolder;

import java.util.ArrayList;

public class RealDetailAdapter extends RecyclerView.Adapter<ViewViewHolder> {


    private ArrayList<String> URLArray;

    @NonNull
    @Override
    public ViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_image, parent, false);
        return new ViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(URLArray.get(position)).placeholder(R.drawable.ic_launcher_background)
                .into(holder.view_image);
    }

    @Override
    public int getItemCount() {
        if (URLArray != null){
            return URLArray.size();
        } else {
            return 0;
        }
    }

    public void setURLArray(ArrayList<String> URLArray) {
        this.URLArray = URLArray;
    }
}
