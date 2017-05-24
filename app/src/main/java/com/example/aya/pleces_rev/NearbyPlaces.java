package com.example.aya.pleces_rev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by aya on 14/05/17.
 */

public class NearbyPlaces extends Fragment {
    private ArrayList<Place> places = new ArrayList<>();
    PlaceAdapter placesAdapter;
    RecyclerView recyclerView;
    private BroadcastReceiver broadcastReceiver;

    public Place currentPlace;

    double lng;
    double lat;

    String TAG = "MainActivity";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.nearby_places, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    lat = intent.getExtras().getDouble("Latitude");
                    lng = intent.getExtras().getDouble("Longitude");
                    placesAdapter = new PlaceAdapter(places, getActivity());

                    new getPlaces(lat, lng).execute();

                }
            };

        }
        IntentFilter filter = new IntentFilter("BROADCASTLOCATION");
        getActivity().registerReceiver(broadcastReceiver, filter);
    }



    @Override
    public void onPause() {
        getActivity().unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class getPlaces extends AsyncTask<Void, Void, Void> {
        double latitude;
        double longitude;
        public getPlaces(double latitude, double longitude){
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            HttpHandler handler = new HttpHandler();
            String api_key = "AIzaSyDWciIGRR3QrUataWADb4g9afMlu271rts";
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"+&radius=500&key=";
            String jsonStr = handler.makeServiceCall(url+api_key);

            Log.e(TAG, "Response from url: " + jsonStr);
            if(jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.e(TAG, "Results: " + results);

                    for (int i = 1; i < results.length(); i++){
                        JSONObject placesRes = results.getJSONObject(i);



                        String icon = placesRes.getString("icon");
                        String id = placesRes.getString("id");
                        String name = placesRes.getString("name");
                        float rating = 0;
                        if (placesRes.has("rating")){
                            rating = (float) placesRes.getDouble("rating");
                        }
                        JSONArray types = placesRes.getJSONArray("types");
                        String [] categories = new String[types.length()];
                        for(int k=0; k < types.length(); k++) {
                            categories[k] = types.getString(k);
                        }
                        String vicinity = placesRes.getString("vicinity");
                        double latitude = placesRes.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                        double longitude = placesRes.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                        Place place = new Place(icon, name, categories, rating, vicinity, latitude, longitude);
                        place.setPlaceID(id);
                        places.add(place);
                    }

                }catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get data from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            placesAdapter = new PlaceAdapter(places, getActivity());
            recyclerView.setAdapter(placesAdapter);
        }
    }
}
