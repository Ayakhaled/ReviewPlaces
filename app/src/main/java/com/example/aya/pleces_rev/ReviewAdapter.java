package com.example.aya.pleces_rev;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aya on 14/05/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHistoryHolder> {
    ArrayList<Review> reviews = new ArrayList<>();
    Context context;

    public ReviewAdapter(ArrayList<Review> reviews, Context context){
        this.reviews = reviews;
        this.context = context;
    }

    @Override
    public ReviewAdapter.ReviewHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_history_item, parent, false);
        return new ReviewAdapter.ReviewHistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewHistoryHolder holder, int position) {
        Review review = reviews.get(position);
        holder.place_name.setText(review.getPlace_name());
        holder.review_title.setText(review.getReview_title());
        holder.review_content.setText(review.getReview_content());
        holder.place_vicinity.setText(review.getPlace_vicinity());
        holder.review_rating_text.setText(String.valueOf(review.getPlace_rating()));
        holder.review_rating_bar.setRating(review.getPlace_rating());
    }

    @Override
    public int getItemCount() {
        return (reviews == null) ? 0 : reviews.size();
    }

    public class ReviewHistoryHolder extends RecyclerView.ViewHolder {
        TextView place_name;
        TextView review_title;
        TextView review_content;
        TextView review_rating_text;
        RatingBar review_rating_bar;
        TextView place_vicinity;
        public ReviewHistoryHolder(View itemView) {
            super(itemView);
            place_name = (TextView) itemView.findViewById(R.id.place_name);
            review_title = (TextView) itemView.findViewById(R.id.review_title);
            review_content = (TextView) itemView.findViewById(R.id.review_content);
            review_rating_text = (TextView) itemView.findViewById(R.id.rating_text);
            review_rating_bar = (RatingBar) itemView.findViewById(R.id.rating_bar);
            place_vicinity = (TextView) itemView.findViewById(R.id.vicinity);
        }
    }
}
