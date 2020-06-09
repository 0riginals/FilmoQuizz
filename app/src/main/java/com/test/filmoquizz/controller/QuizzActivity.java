package com.test.filmoquizz.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.test.filmoquizz.R;
import com.test.filmoquizz.model.QuestionBank;
import com.test.filmoquizz.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class QuizzActivity extends AppCompatActivity implements View.OnClickListener {

    private RequestQueue queue;

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "?api_key=f68f576bcdf9846f104b46cb942dd9ed";
    private static final String LANGUAGE = "&language=fr-FR";

    private Question currentQuestion;
    private QuestionBank questionBank;
    private ArrayList<Question> questions;

    private TextView questionView;
    private ImageView imageView;
    private Button answer1View;
    private Button answer2View;
    private Button answer3View;

    private int numberOfQuestion;
    private int score;

    private ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        score = 0;
        numberOfQuestion = 5;
        questions = new ArrayList<>();

        questionView = (TextView) findViewById(R.id.activity_quizz_question);
        answer1View = (Button) findViewById(R.id.activity_quizz_answer1);
        answer2View = (Button) findViewById(R.id.activity_quizz_answer2);
        answer3View = (Button) findViewById(R.id.activity_quizz_answer3);

        answer1View.setTag(0);
        answer2View.setTag(1);
        answer3View.setTag(2);

        queue = Volley.newRequestQueue(QuizzActivity.this);

        this.generateQuestions();
        //currentQuestion = questionBank.getQuestion();
        //displayQuestion(currentQuestion);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
    }

    public void displayQuestion(Question question) {
        Picasso.get().load(question.getUrlImage()).into(imageView);
        answer1View.setText(question.getChoices().get(0));
        answer2View.setText(question.getChoices().get(1));
        answer3View.setText(question.getChoices().get(2));
    }

    private void generateQuestions() {
        int randomPage = (int) (120.0 * Math.random());
        if(randomPage == 0) {
            randomPage = 1;
        }
        String discoverURL = BASE_URL + "discover/movie" + API_KEY + LANGUAGE + "&page=" + randomPage;
        // System.out.println("GET sur l'url: " + discoverURL);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, discoverURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    // Traite l'ensemble des résultas trouvé dans la liste
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject result = jsonArray.getJSONObject(i);
                        // Utile pour trouver les films similaires
                        int movieId = result.getInt("id");
                        // Correspond à la bonne réponse
                        String title = result.getString("title");
                        String urlImage = result.getString("backdrop_path");
                        // On créer notre question et on vient hydrater sa liste de choix après
                        Question question = new Question(movieId, urlImage, title);
                        findOtherTitles(question);
                        questions.add(question);
                    }
                    System.out.println("Nos questions: " + questions);
                    System.out.println("nbr de questions: " + questions.size());
                    // Au final tout le traitement se fait après la réponse de celui là et la recherche des autres titres similaires
                    // TODO: compliqué le questionBank pour le moment, a voir avec du sommeil demain
                    questionBank = new QuestionBank(questions);
                }catch(JSONException e) {
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

    private void findOtherTitles(final Question question) {
        // On prends en paramètre un objet Question contenant déjà des valeurs sur ses attributs
        // Le but est de venir ajouter des titres à notre question
        // Ce qui est cool c'est que si on a une exception on ne traitera pas la question et donc on ne l'ajoutera pas à notre bank
        String similarURL = BASE_URL + "movie/" + question.getQuestionId() + "/similar" + API_KEY + LANGUAGE + "&page=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, similarURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("On traite : " + question.getResponse());
                    JSONArray jsonArray = response.getJSONArray("results");
                    for(int i = 0; i < 2; i++) {
                        // On ne prends que 2 résultats car au final on affiche 3 réponses
                        JSONObject result = jsonArray.getJSONObject(i);
                        String title = result.getString("title");
                        System.out.println("Similarité trouvé: " + title);
                        question.addChoice(title);
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
        queue.add(request);
    }

}