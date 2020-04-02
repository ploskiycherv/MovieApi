package com.example.movieapi.model;

public class MovieDescription {

    private String title, year, rating, posterUrl, backdropPathUrl, id, description;

    public MovieDescription(String title, String year, String rating, String posterUrl, String backdropPathUrl, String id, String description) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.posterUrl = posterUrl;
        this.backdropPathUrl = backdropPathUrl;
        this.id = id;
        this.description = description;
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

    public String getBackdropPathUrl() {
        return backdropPathUrl;
    }

    public void setBackdropPathUrl(String backdropPathUrl) {
        this.backdropPathUrl = backdropPathUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
