package com.example.searchbot.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.searchbot.R;

public class AnswerTextViewHolder extends BaseViewHolder {

    public TextView naver_text;

    public AnswerTextViewHolder(@NonNull View itemView) {
        super(itemView);
        this.layout = itemView.findViewById(R.id.textanswer);
        this.profile = itemView.findViewById(R.id.naver_profile);
        this.profile_name = itemView.findViewById(R.id.naver_name);
        this.naver_text = itemView.findViewById(R.id.naver_text);
        profile.setImageResource(R.drawable.ic_naver_icon__59879);
        profile_name.setText("네이버");
    }
}
