package com.example.aya.pleces_rev;

import android.app.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by aya on 22/05/17.
 */

public interface ServiceHttp {
    @POST("auth/signup")
    Call<Response> addUser(@Body Request request);

    @POST("auth/login")
    Call<Response> login(@Body Request request);

    @POST("review")
    Call<Response> addReview(@Body Request request, @Header("Authorization") String token);

    @GET("reviews")
    Call<ArrayList<Review>> getReviews(@Header("Authorization") String token);


    class Creator{
        public static ServiceHttp getService(boolean googleApi){
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit;

            if (googleApi){
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://maps.googleapis.com/maps/api/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
            else {
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-54-149-204-201.us-west-2.compute.amazonaws.com/api/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
            return retrofit.create(ServiceHttp.class);
        }
    }
}
