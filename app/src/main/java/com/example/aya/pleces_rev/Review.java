package com.example.aya.pleces_rev;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aya on 14/05/17.
 */

public class Review implements Serializable {
    private String place_name;
    private String title;
    @SerializedName("review")
    private String review_content;
    @SerializedName("rating")
    private float place_rating;
    @SerializedName("address")
    private String place_vicinity;
    private String place_id;
    private double lat;
    private double lng;

    public Review(String review_title, String place_vicinity, String review_content, String place_id, float place_rating, String place_name) {
        this.place_name = place_name;
        this.title = review_title;
        this.review_content = review_content;
        this.place_rating = place_rating;
        this.place_vicinity = place_vicinity;
        this.place_id = place_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getReview_title() {
        return title;
    }

    public void setReview_title(String review_title) {
        this.title = review_title;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public float getPlace_rating() {
        return place_rating;
    }

    public void setPlace_rating(float place_rating) {
        this.place_rating = place_rating;
    }

    public String getPlace_vicinity() {
        return place_vicinity;
    }

    public void setPlace_vicinity(String place_vicinity) {
        this.place_vicinity = place_vicinity;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
