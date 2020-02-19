package com.example.searchbot.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.searchbot.CustomData.source;
import com.example.searchbot.R;
import com.example.searchbot.ViewHolders.AnswerElseViewHolder;
import com.example.searchbot.ViewHolders.AnswerImageViewHolder;
import com.example.searchbot.ViewHolders.AnswerTextViewHolder;
import com.example.searchbot.ViewHolders.BaseViewHolder;
import com.example.searchbot.ViewHolders.RequestViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<source> TypeArray;
    private String user_name;
    private String user_profile;

    public void setTypeArray(ArrayList<source> typeArray) {
        this.TypeArray = typeArray;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_profile(String user_profile) {
        this.user_profile = user_profile;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ItemType.TYPE_REQUEST) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request, parent, false);
            return new RequestViewHolder(view);
        } else if (viewType == ItemType.TYPE_TEXT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_text, parent, false);
            return new AnswerTextViewHolder(view);
        } else if (viewType == ItemType.TYPE_IMG) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_image, parent, false);
            return new AnswerImageViewHolder(view);
        } else if (viewType == ItemType.TYPE_BLOG || viewType == ItemType.TYPE_NEWS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_else, parent, false);
            return new AnswerElseViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (getItemViewType(position) == ItemType.TYPE_REQUEST) {
//            ((RequestViewHolder) holder).profile_name.setText(user_name);
            ((RequestViewHolder) holder).profile_name.setText(TypeArray.get(position).src2);
            Glide.with(holder.itemView.getContext()).load(TypeArray.get(position).src3).
                    into(((RequestViewHolder) holder).profile);
            ((RequestViewHolder) holder).query.setText(TypeArray.get(position).src1);
        } else if (getItemViewType(position) == ItemType.TYPE_TEXT) {
            ((AnswerTextViewHolder) holder).naver_text.setText(TypeArray.get(position).src1);
        } else if (getItemViewType(position) == ItemType.TYPE_IMG) {
            Glide.with(holder.itemView.getContext()).load(TypeArray.get(position).src2)
                    .into(((AnswerImageViewHolder) holder).image1);
            Glide.with(holder.itemView.getContext()).load(TypeArray.get(position).src3)
                    .into(((AnswerImageViewHolder) holder).image2);
//            ((AnswerImageViewHolder) holder).ImageList = TypeArray.get(position).src4;
            ((AnswerImageViewHolder) holder).query = TypeArray.get(position).src1;
        } else if (getItemViewType(position) == ItemType.TYPE_BLOG) {
            ((AnswerElseViewHolder) holder).elsetitle.setText(TypeArray.get(position).src1);
            ((AnswerElseViewHolder) holder).elsecontent.setText(TypeArray.get(position).src2);
            ((AnswerElseViewHolder) holder).link = TypeArray.get(position).src3;
            ((AnswerElseViewHolder) holder).elsemore.setText("블로그 더보기 >");
        } else {
            ((AnswerElseViewHolder) holder).elsetitle.setText(TypeArray.get(position).src1);
            ((AnswerElseViewHolder) holder).elsecontent.setText(TypeArray.get(position).src2);
            ((AnswerElseViewHolder) holder).link = TypeArray.get(position).src3;
            ((AnswerElseViewHolder) holder).elsemore.setText("뉴스 더보기 >");
        }
    }

    @Override
    public int getItemCount() {
        if (TypeArray != null) {
            return TypeArray.size();
        } else {
            return 0;
        }
    }

    public class ItemType {
        static final int TYPE_REQUEST = 0;
        static final int TYPE_TEXT = 1;
        static final int TYPE_IMG = 2;
        static final int TYPE_BLOG = 3;
        static final int TYPE_NEWS = 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (TypeArray.get(position).type == ItemType.TYPE_REQUEST) {
            return ItemType.TYPE_REQUEST;
        } else if (TypeArray.get(position).type == ItemType.TYPE_TEXT) {
            return ItemType.TYPE_TEXT;
        } else if (TypeArray.get(position).type == ItemType.TYPE_IMG) {
            return ItemType.TYPE_IMG;
        } else if (TypeArray.get(position).type == ItemType.TYPE_BLOG) {
            return ItemType.TYPE_BLOG;
        } else {
            return ItemType.TYPE_NEWS;
        }
    }
}
