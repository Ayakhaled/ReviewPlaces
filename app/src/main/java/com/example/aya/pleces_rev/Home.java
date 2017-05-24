package com.example.aya.pleces_rev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by aya on 14/05/17.
 */

public class Home extends Fragment{
    NearbyPlaces nearbyPlaces = new NearbyPlaces();
    String placeName = " ";
    private TextView currentPlace;
    private RatingBar ratingBar;
    private EditText revTitle;
    private EditText revContent;
    private Button submitRev;

    private String current_place_vicinity;
    private String current_place_name;

    String token = " ";


    String TAG = ".Home";
    private BroadcastReceiver broadcastReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.home, container, false);

        token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("TOKEN", "hhhhhh");

        currentPlace = (TextView) rootView.findViewById(R.id.current_place);
        ratingBar = (RatingBar) rootView.findViewById(R.id.rating_bar);
        revTitle = (EditText) rootView.findViewById(R.id.review_title);
        revContent = (EditText) rootView.findViewById(R.id.review_content);
        submitRev = (Button) rootView.findViewById(R.id.submit_rev);

        String title = String.valueOf(revTitle.getText());
        String content = String.valueOf(revContent.getText());


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

//        Request request = new Request();
//        ServiceHttp.Creator.getService(true).getCurrentPlace()
//                .enqueue(new Callback<Response>() {
//                    @Override
//                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                        System.out.println("Aya khaled: "+response.code());
//                        if (response.code() == 200){
//                            System.out.println("Aya khaled: "+response);
//                        }
//                        else {
//                            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Response> call, Throwable t) {
//                        System.out.println("aya khaled: Failed"+t.getMessage());
//                    }
//                });

        submitRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request(String.valueOf(revTitle.getText()), "Abbassya", String.valueOf(revContent.getText()), "ChIJIQVjIp8_WBQRm0bJ4qXAKXs", ratingBar.getRating(), "Faculty of Engineering-ASU");
                ServiceHttp.Creator.getService(false).addReview(request, "Bearer "+token)
                        .enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                System.out.println("Aya khaled: "+response.code());
                                if(response.isSuccessful()){
                                    System.out.println("Aya khaled"+ placeName);
                                    if (response.code() == 200){

//                                        try {
//                                            System.out.println("Aya khaled: "+response.body());
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        }
                                    }
                                    else {
                                        Toast.makeText(getActivity(), "This email is already registered try another one", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    try {
                                        System.out.println("Aya khaled: "+response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                System.out.println("aya khaled: Failed"+t.getMessage());
                            }
                        });
            }
        });
    }

}
