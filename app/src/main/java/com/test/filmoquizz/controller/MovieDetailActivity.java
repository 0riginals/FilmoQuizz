package com.test.filmoquizz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.test.filmoquizz.R;
import com.test.filmoquizz.model.Movie;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "?api_key=f68f576bcdf9846f104b46cb942dd9ed";
    private static final String LANGUAGE = "&language=fr-FR";

    private RequestQueue queue;
    private Movie movie;
    private ImageView imageView;
    private TextView titleView;
    private TextView overviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        queue = Volley.newRequestQueue(MovieDetailActivity.this);

        Intent intent = getIntent();
        int movieId = intent.getIntExtra("id", -1);
        imageView = (ImageView) findViewById(R.id.activity_movie_detail_img);
        titleView = (TextView) findViewById(R.id.activity_movie_detail_title);
        overviewView = (TextView) findViewById(R.id.activity_movie_detail_overview);

        fetchMovie(movieId);
    }

    public void fetchMovie(int movieId) {
        String movieUrl = "movie/" + movieId;
        String url = BASE_URL + movieUrl + API_KEY + LANGUAGE;
        System.out.println("GET sur l'url: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int movieId = response.getInt("id");
                    String urlImage = response.getString("poster_path");
                    String title = response.getString("title");
                    String overview = response.getString("overview");
                    if(overview.isEmpty())
                        overview = "Ce film n'a pas de description";
                    movie = new Movie(movieId, title, urlImage, overview);
                    Picasso.get().load(movie.getUrlImage()).into(imageView);
                    titleView.setText(movie.getTitle());
                    overviewView.setText(movie.getOverview());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}