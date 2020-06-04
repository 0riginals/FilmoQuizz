package com.test.filmoquizz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.test.filmoquizz.R;

public class HomeActivity extends AppCompatActivity {

    // Elements de HomeActivity
    private ImageButton playQuizzButton;
    private ImageButton moviesListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playQuizzButton = (ImageButton) findViewById(R.id.activity_home_play_quizz);
        moviesListButton = (ImageButton) findViewById(R.id.activity_home_movies_list);

        playQuizzButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // méthode permettant de changer d'activité après le clique sur l'image
                Intent quizzActivityIntent = new Intent(HomeActivity.this, QuizzActivity.class);
                startActivity(quizzActivityIntent);
            }
        });
        moviesListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moviesActivityIntent = new Intent(HomeActivity.this, MoviesActivity.class);
                startActivity(moviesActivityIntent);
            }
        });
    }


}