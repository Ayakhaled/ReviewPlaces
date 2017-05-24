package com.example.aya.pleces_rev;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by aya on 22/05/17.
 */

public class Response {
    @SerializedName("token")
    public String token;

    @SerializedName("name")
    public String place_name;

    @SerializedName("vicinity")
    public String place_vicinity;

    @SerializedName("result")
    public JSONArray result;

    @SerializedName("reviews")
    public ArrayList<Review> reviews;
}
