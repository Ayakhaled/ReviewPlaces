package com.example.aya.pleces_rev;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

import static android.R.attr.id;

/**
 * Created by aya on 14/05/17.
 */

public class Profile extends Fragment {
    String TAG = ".Profile";
    private TextView name;
    private TextView username;
    private TextView location;
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private ArrayList<Review> reviews = new ArrayList<>();

    String token = " ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile, container, false);

        token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "hhhhhh");

        name = (TextView) rootView.findViewById(R.id.user_profile_name);
        location = (TextView) rootView.findViewById(R.id.current_place);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.prev_rev);

        Request request = new Request();
        ServiceHttp.Creator.getService(false).getReviews("Bearer "+token)
                .enqueue(new Callback<ArrayList<Review>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Review>> call, retrofit2.Response<ArrayList<Review>> response) {
                        System.out.println("Home aya: "+response.code());
                        if (response.code() == 200){
                            System.out.println("Home aya "+response.body().size());
                            for (int i = 0; i < response.body().size(); i++){
                                String place_id = response.body().get(i).getPlace_id();
                                String place_name = response.body().get(i).getPlace_name();
                                String address = response.body().get(i).getPlace_vicinity();
                                String review = response.body().get(i).getReview_content();
                                String title = response.body().get(i).getReview_title();
                                float rating = response.body().get(i).getPlace_rating();

                                reviews.add(new Review(title, address, review, place_id, rating, place_name));
                            }

                        }
                        else {
                            Toast.makeText(getActivity(), "This email is already registered try another one", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Review>> call, Throwable t) {
                        System.out.println("aya khaled: Failed"+t.getMessage());
                    }
                });

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        reviewAdapter = new ReviewAdapter(reviews, getActivity());
        recyclerView.setAdapter(reviewAdapter);
        return rootView;
    }
}
