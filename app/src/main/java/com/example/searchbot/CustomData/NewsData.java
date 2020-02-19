package com.example.searchbot.CustomData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsData {
    @SerializedName("items")
    public ArrayList<items> items;

    public class items {
        @SerializedName("title")
        public String title;
        @SerializedName("link")
        public String link;
        @SerializedName("description")
        public String description;
    }
}
