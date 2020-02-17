package com.example.searchbot.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchbot.R;

public class ViewViewHolder extends RecyclerView.ViewHolder {

    public ImageView view_image;


    public ViewViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view_image = itemView.findViewById(R.id.RealDetail);
    }
}
