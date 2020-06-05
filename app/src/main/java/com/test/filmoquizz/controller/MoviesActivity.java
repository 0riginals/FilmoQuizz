package com.test.filmoquizz.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.test.filmoquizz.R;
import java.util.LinkedList;
import model.Movie;

public class MoviesActivity extends AppCompatActivity {

    LinkedList<Movie> moviesList = new LinkedList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Connexion de la ListView au layout
        ListView moviesListView = (ListView) findViewById(R.id.activity_movies_list);

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
    }

    AdapterView.OnItemClickListener listview_listerner = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        }
    };
}