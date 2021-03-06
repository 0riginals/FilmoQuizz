package com.test.filmoquizz.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.test.filmoquizz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.test.filmoquizz.model.Movie;


/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class MoviesActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "?api_key=f68f576bcdf9846f104b46cb942dd9ed";
    private static final String LANGUAGE = "&language=fr-FR";

    private String filterSearch = "";

    private ArrayList<Movie> movies;
    private ListView listView;
    private SearchView searchView;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        movies = new ArrayList<>();
        listView = (ListView) findViewById(R.id.activity_movies_list);
        searchView = (SearchView) findViewById(R.id.searchView);
        queue = Volley.newRequestQueue(MoviesActivity.this);
        this.fetchMovies(filterSearch);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                fetchMovies(newText);
                return false;
            }
        });
    }

    private void fetchMovies(String searchMovie) {
        if (searchMovie.isEmpty()) filterSearch = "movie/popular";
        else
            filterSearch = "search/movie";
        String url = BASE_URL + filterSearch + API_KEY + LANGUAGE + "&page=1" + "&query=" + searchMovie;
        System.out.println("GET à l'adresse: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int maxResult = 0;
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    maxResult = Math.min(jsonArray.length(), 10);
                    movies.clear();
                    for (int i = 0; i < maxResult; i++) {
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("title");
                        int movieId = result.getInt("id");
                        String overview = result.getString("overview");
                        String urlImage = result.getString("poster_path");
                        if (overview.isEmpty())
                            overview = "Ce film ne possède pas encore de description";
                        movies.add(new Movie(movieId, title, urlImage, overview));
                    }
                    MovieAdapter adapter = new MovieAdapter(MoviesActivity.this, R.layout.activity_movie_item, movies);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                            intent.putExtra("id", movies.get(position).getId());
                            startActivity(intent);
                        }
                    });

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