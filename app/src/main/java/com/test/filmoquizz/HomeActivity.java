package com.test.filmoquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    // Elements de HomeActivity
    private ImageButton playQuizzButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playQuizzButton = (ImageButton) findViewById(R.id.playQuizz);


        playQuizzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // méthode permettant de changer d'activité après le clique sur l'image
            public void onClick(View v) {
                Intent quizzActivityIntent = new Intent(HomeActivity.this, QuizzActivity.class);
                startActivity(quizzActivityIntent);
            }
        });
    }


}