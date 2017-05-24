package com.example.aya.pleces_rev;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aya on 14/05/17.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {
    private ArrayList<Place> places = new ArrayList<>();
    private Context context;

    public PlaceAdapter(ArrayList<Place> places, Context context){
        this.places = places;
        this.context = context;
    }

    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);
        return new PlaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        Place place = places.get(position);

        String categories = "Categories: ";
        for (int i = 0; i < place.getPlace_categories().length; i++){
            categories += place.getPlace_categories()[i];
            if (i != (place.getPlace_categories().length-1)){
                categories += ", ";
            }
        }

        holder.place_name.setText(place.getPlace_name());
        holder.place_categories.setText(categories);
        holder.place_rating_text.setText(String.valueOf(place.getRating()));
        holder.vicinity.setText(place.getVicinity());
        holder.place_rating_bar.setRating(place.getRating());
        Picasso.with(context)
                .load(place.getIcon())
                .placeholder(R.color.colorAccent)
                .into(holder.place_icon);
    }

    @Override
    public int getItemCount() {
        return (places == null) ? 0 : places.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {
        TextView place_name;
        TextView place_categories;
        ImageView place_icon;
        TextView place_rating_text;
        RatingBar place_rating_bar;
        TextView vicinity;

        public PlaceHolder(View itemView) {
            super(itemView);
            place_name = (TextView) itemView.findViewById(R.id.place_name);
            place_categories = (TextView) itemView.findViewById(R.id.place_category);
            place_icon = (ImageView) itemView.findViewById(R.id.place_icon);
            place_rating_text = (TextView) itemView.findViewById(R.id.place_rate_text);
            place_rating_bar = (RatingBar) itemView.findViewById(R.id.place_rating_bar);
            vicinity = (TextView) itemView.findViewById(R.id.vicinity);
        }
    }
}
