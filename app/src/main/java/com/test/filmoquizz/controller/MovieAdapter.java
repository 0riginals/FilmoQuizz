package com.test.filmoquizz.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import com.test.filmoquizz.R;
import com.test.filmoquizz.model.Movie;

import java.util.ArrayList;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
   private Context context;
   private ArrayList<Movie> movies;

   private TextView titleView;
   private ImageView imageView;
   private TextView overviewView;

   public MovieAdapter(Context context, int resource, ArrayList<Movie> movies) {
       super(context, resource, movies);
       this.context = context;
       this.movies = movies;
   }

   @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if(convertView == null) {
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.activity_movie_item, parent, false);
       }

       titleView = (TextView) convertView.findViewById(R.id.activity_movie_detail_title);
       imageView = (ImageView) convertView.findViewById(R.id.activity_movie_detail_img);
       overviewView = (TextView) convertView.findViewById(R.id.activity_movie_detail_overview);

       titleView.setText(movies.get(position).getTitle());
       Picasso.get().load(movies.get(position).getUrlImage()).into(imageView);
       overviewView.setText(movies.get(position).getOverview());

       return convertView;
   }

}