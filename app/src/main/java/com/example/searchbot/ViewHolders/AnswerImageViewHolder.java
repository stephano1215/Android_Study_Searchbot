package com.example.searchbot.ViewHolders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.searchbot.ImageDetailActivity;
import com.example.searchbot.R;

public class AnswerImageViewHolder extends BaseViewHolder {

    public ImageView image1;
    public ImageView image2;
    public LinearLayout image_result;
    public String query;

    public AnswerImageViewHolder(@NonNull final View itemView) {
        super(itemView);
        this.layout = itemView.findViewById(R.id.imageanswer);
        this.profile = itemView.findViewById(R.id.img_naver_profile);
        this.profile_name = itemView.findViewById(R.id.img_naver_name);
        this.image1 = itemView.findViewById(R.id.image1);
        this.image2 = itemView.findViewById(R.id.image2);
        this.image_result = itemView.findViewById(R.id.image_result);
        profile.setImageResource(R.drawable.ic_naver_icon__59879);
        profile_name.setText("네이버");
        image_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageDetailActivity.class);
                intent.putExtra("query", query);
                v.getContext().startActivity(intent);
            }
        });
    }
}
