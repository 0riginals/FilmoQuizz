package com.test.filmoquizz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.test.filmoquizz.R;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class HomeActivity extends AppCompatActivity {

    // Les préferences utilisateurs
    private SharedPreferences preferences;

    // Elements de HomeActivity
    private ImageButton playQuizzButton;
    private ImageButton moviesListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playQuizzButton = (ImageButton) findViewById(R.id.activity_home_play_quizz);
        moviesListButton = (ImageButton) findViewById(R.id.activity_home_movies_list);

        preferences = getPreferences(MODE_PRIVATE);
        Integer userId = preferences.getInt("user_id", -1);

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