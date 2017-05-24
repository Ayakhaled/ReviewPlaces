package com.example.aya.pleces_rev;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aya on 22/05/17.
 */

public class Request {
    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("title")
    public String title;

    @SerializedName("address")
    public String address;

    @SerializedName("review")
    public String review;

    @SerializedName("place_id")
    public String place_id;

    @SerializedName("rating")
    public float rating;

    @SerializedName("place_name")
    public String place_name;

    public Request(String name, String email, String password) {
        this.name = name;
        this.email =  email;
        this.password =  password;
    }

    public Request(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Request(String title, String address, String review, String place_id, float rating, String place_name) {
        this.title = title;
        this.address = address;
        this.review = review;
        this.place_id = place_id;
        this.rating = rating;
        this.place_name = place_name;
    }

    public Request(){}
}
