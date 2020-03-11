package com.example.movieapi.model;

public class MovieItem {

    private String title, year, rating, posterUrl, id;

    public MovieItem(String title, String year, String rating, String posterUrl, String id) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
