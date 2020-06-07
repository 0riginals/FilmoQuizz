package com.test.filmoquizz.model;

/**
 * Created by Paul VINOT, Antoine COLPAERT, Yuting JIN
 */
public class Movie {
    private int id;
    private String title;
    private String urlImage;
    private String overview;
    private String director;
    private int year;
    private String actors;
    private int image;

    private String BASE_URL_IMG = "https://image.tmdb.org/t/p/original";

    public Movie() {
        // Constructeur par défault, vide, utile pour ORMLite
    }

    // Premier constructeur qui nous permettra l'apercu dans la liste des films
    public Movie(int id, String title, String urlImage, String overview) {
        this.id = id;
        this.urlImage = BASE_URL_IMG + urlImage;
        this.title = title;
        this.overview = overview;
    }

    public Movie(String title, String urlImage, String overview, String director, int year, String actors, int image) {
        this.title = title;
        this.urlImage = urlImage;
        this.overview = overview;
        this.director = director;
        this.year = year;
        this.actors = actors;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
