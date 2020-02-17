package com.example.searchbot.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageData {
    @SerializedName("lastBuildDate")
    public String lastBuildDate;
    @SerializedName("total")
    public Integer total;
    @SerializedName("start")
    public Integer start;
    @SerializedName("display")
    public Integer display;
    @SerializedName("items")
    public List<items> items;

    public class items {
        @SerializedName("title")
        public String title;
        @SerializedName("link")
        public String link;
        @SerializedName("thumbnail")
        public String thumbnail;
        @SerializedName("sizeheight")
        public String sizeheight;
        @SerializedName("sizewidth")
        public String sizewidth;
    }
}
