package com.example.searchbot.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchbot.R;

public class DetailViewHolder extends RecyclerView.ViewHolder {

    public ImageView detail;

    public DetailViewHolder(@NonNull View itemView) {
        super(itemView);
        this.detail = itemView.findViewById(R.id.detail);
    }
}
