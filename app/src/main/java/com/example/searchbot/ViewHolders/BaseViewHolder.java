package com.example.searchbot.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public ImageView profile;
    public TextView profile_name;
    protected ConstraintLayout layout;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
