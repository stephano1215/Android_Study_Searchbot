package com.example.searchbot.ViewHolders;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.searchbot.R;


public class AnswerElseViewHolder extends BaseViewHolder {

    public TextView elsetitle;
    public TextView elsecontent;
    public TextView elsemore;
    protected LinearLayout result;
    public String link;

    public AnswerElseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.layout = itemView.findViewById(R.id.elseanswer);
        this.profile = itemView.findViewById(R.id.else_naver_profile);
        this.profile_name = itemView.findViewById(R.id.else_naver_name);
        this.elsetitle = itemView.findViewById(R.id.else_title);
        this.elsecontent = itemView.findViewById(R.id.else_content);
        this.elsemore = itemView.findViewById(R.id.else_more);
        this.result = itemView.findViewById(R.id.result);
        profile.setImageResource(R.drawable.ic_naver_icon__59879);
        profile_name.setText("네이버");
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                v.getContext().startActivity(intent);
            }
        });
    }
}
