package com.example.searchbot.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.searchbot.R;

public class RequestViewHolder extends BaseViewHolder {

    public TextView query;

    public RequestViewHolder(@NonNull View itemView) {
        super(itemView);
        this.layout = itemView.findViewById(R.id.userrequest);
        this.profile = itemView.findViewById(R.id.profile);
        this.profile_name = itemView.findViewById(R.id.profile_name);
        this.query = itemView.findViewById(R.id.query);
        profile.setImageResource(R.drawable.ic_naver_icon__59879);
    }
}
