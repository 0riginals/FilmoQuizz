package com.test.filmoquizz.controller;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.test.filmoquizz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import com.test.filmoquizz.model.Movie;

public class MoviesActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "?api_key=f68f576bcdf9846f104b46cb942dd9ed";
    private static final String LANGUAGE = "&language=fr-FR";

    LinkedList<Movie> moviesList = new LinkedList<Movie>();

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ListView moviesListView = (ListView) findViewById(R.id.activity_movies_list);
        fetchMovies();
        /*
        // Ajout de films en brut dans moviesList
        moviesList.add(new Movie("Interstellar", R.drawable.interstellar, "Alors que la Terre se meurt, une équipe d'astronautes franchit un trou de ver apparu près de Saturne et conduisant à une autre galaxie, afin d'explorer un nouveau système stellaire et dans l'espoir de trouver une planète habitable et y établir une colonie spatiale pour sauver l'humanité.", "Christopher Nolan", 2014, "Matthew McConaughey"));
        moviesList.add(new Movie("Blade Runner 2049", R.drawable.bladerunner, "Blade Runner 2049 raconte les aventures d'un « blade runner » (policier chargé de traquer les réplicants, des androïdes créés à l'image de l'Homme), trente ans après les aventures de Rick Deckard.", "Denis Villeneuve", 2017, "Ryan Gosling"));
        moviesList.add(new Movie("Taxis Driver", R.drawable.taxisdriver, "Travis Bickle, un jeune homme du Midwest et ancien marine, est chauffeur de taxi de nuit à New York. Insomniaque et solitaire, il rencontre Betsy, une assistante du sénateur Charles Palantine, candidat à la présidentielle, mais elle le repousse après qu'il l'a emmenée voir un film pornographique. Renvoyé à sa solitude et confronté à la violence et à la perversion de la nuit new-yorkaise, il achète des armes au marché noir et s’entraîne à les manier.", "Martin Scorsese", 1976, "Robert De Niro"));
        moviesList.add(new Movie("Taxis Driver", R.drawable.taxisdriver, "Travis Bickle, un jeune homme du Midwest et ancien marine, est chauffeur de taxi de nuit à New York. Insomniaque et solitaire, il rencontre Betsy, une assistante du sénateur Charles Palantine, candidat à la présidentielle, mais elle le repousse après qu'il l'a emmenée voir un film pornographique. Renvoyé à sa solitude et confronté à la violence et à la perversion de la nuit new-yorkaise, il achète des armes au marché noir et s’entraîne à les manier.", "Martin Scorsese", 1976, "Robert De Niro"));
        moviesList.add(new Movie("Taxis Driver", R.drawable.taxisdriver, "Travis Bickle, un jeune homme du Midwest et ancien marine, est chauffeur de taxi de nuit à New York. Insomniaque et solitaire, il rencontre Betsy, une assistante du sénateur Charles Palantine, candidat à la présidentielle, mais elle le repousse après qu'il l'a emmenée voir un film pornographique. Renvoyé à sa solitude et confronté à la violence et à la perversion de la nuit new-yorkaise, il achète des armes au marché noir et s’entraîne à les manier.", "Martin Scorsese", 1976, "Robert De Niro"));

        // Création d'un adapter qui va créer le lien entre la liste de films et chaque objet de film des layouts
        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), R.layout.activity_movie_item, moviesList);

        // Mise à jour de la ListView en fonction de notre movieAdapter
        moviesListView.setAdapter(movieAdapter);
        moviesListView.setOnItemClickListener(listview_listerner);
         */
    }

    /*
    AdapterView.OnItemClickListener listview_listerner = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        }
    };

         */
    private void fetchMovies() {
        String popularMovies = "movie/popular";
        String url = BASE_URL + popularMovies + API_KEY + LANGUAGE + "&page=1";
        System.out.println("GET à l'adresse: " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int maxResult = 0;
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    maxResult = Math.min(jsonArray.length(), 10);
                    for (int i = 0; i < maxResult; i++) {
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("title");
                        String overview = result.getString("overview");
                        if(overview.isEmpty())
                            overview = "Ce film ne possède pas encore de description";
                        // TODO: Formatté les données pour l'envoyer dans la listview
                        // TODO: Test pour le moment

                    }
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }
}