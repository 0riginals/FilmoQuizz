package com.test.filmoquizz.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.filmoquizz.R;

import java.util.LinkedList;

import model.Movie;


public class MovieAdapter extends ArrayAdapter<Movie> {
    private final Context _context;
    private LinkedList<Movie> _movies;

    public MovieAdapter(Context context, int resource, LinkedList<Movie> movies) {
        super(context, resource, movies);
        _context = context;
        _movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_movie_item, parent, false);
        } else {
            convertView = (LinearLayout) convertView;
        }

        ImageView imageMovie = (ImageView) convertView.findViewById(R.id.activity_movie_item_image);
        imageMovie.setBackgroundResource(_movies.get(position).getImage());

        TextView titleMovie = (TextView) convertView.findViewById(R.id.activity_movie_item_title);
        titleMovie.setText(_movies.get(position).getTitle());
        titleMovie.setTag(_movies.get(position).getTitle());

        return convertView;
    }
}