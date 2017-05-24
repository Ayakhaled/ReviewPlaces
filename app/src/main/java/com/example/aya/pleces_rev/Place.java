package com.example.aya.pleces_rev;

import java.io.Serializable;

/**
 * Created by aya on 14/05/17.
 */

public class Place implements Serializable {
    private String icon;
    private String place_name;
    private String [] place_categories;
    private float rating;
    private String vicinity;
    private double lat, lng;
    private String placeID;

    public Place(String icon, String place_name, String[] place_categories, float rating, String vicinity, double lat, double lng) {
        this.icon = icon;
        this.place_name = place_name;
        this.place_categories = place_categories;
        this.rating = rating;
        this.vicinity = vicinity;
        this.lat = lat;
        this.lng = lng;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String[] getPlace_categories() {
        return place_categories;
    }

    public void setPlace_categories(String[] place_categories) {
        this.place_categories = place_categories;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
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

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }
}
