package com.example.searchbot.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.example.searchbot.R;
import com.example.searchbot.ViewHolders.DetailViewHolder;

public class ItemAdapter extends PagedListAdapter<String, DetailViewHolder> {

    ItemAdapter(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_detail, parent,false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        String detail = getItem(position);
        if (detail != null) {
            Glide.with(holder.itemView.getContext()).load(detail)
                    .into(holder.detail);
        }
        else {
            Toast.makeText(holder.itemView.getContext(), "Detail Null", Toast.LENGTH_SHORT).show();
        }
    }

    private static DiffUtil.ItemCallback<String> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
