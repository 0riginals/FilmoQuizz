package com.test.filmoquizz.model;

import java.util.ArrayList;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class Question {
    private int questionId;
    private String urlImage;
    private ArrayList<String> choices;
    private String response;

    private final static String BASE_URL_IMG = "https://image.tmdb.org/t/p/original";

    public Question(int questionId, String urlImage, String response) {
        this.questionId = questionId;
        this.urlImage = BASE_URL_IMG + urlImage;
        this.response = response;
        this.choices = new ArrayList<>();
        this.choices.add(response);
    }

    public Question(int questionId, String urlImage, ArrayList<String> choices, String response){
        this.questionId = questionId;
        this.urlImage = BASE_URL_IMG + urlImage;
        this.choices = choices;
        this.response = response;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int id) {
        this.questionId = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        if(choices == null)
            throw new IllegalArgumentException("Array cannot be null");
        this.choices = choices;
    }

    public void addChoice(String choice) {
        if(this.choices == null)
            this.choices = new ArrayList<String>();
        this.choices.add(choice);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
