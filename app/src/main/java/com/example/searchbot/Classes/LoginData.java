package com.example.searchbot.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginData {
    @SerializedName("resultcode")
    public String resultcode;
    @SerializedName("message")
    public String message;
    @SerializedName("response")
    public items items;

    public class items {
        @SerializedName("id")
        public String id;
        @SerializedName("nickname")
        public String nickname;
        @SerializedName("name")
        public String name;
        @SerializedName("email")
        public String email;
        @SerializedName("gender")
        public String gender;
        @SerializedName("age")
        public String age;
        @SerializedName("birthday")
        public String birthday;
        @SerializedName("profile_image")
        public String profile_image;
    }
}
