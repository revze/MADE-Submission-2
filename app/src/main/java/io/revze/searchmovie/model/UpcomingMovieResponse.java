package io.revze.searchmovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingMovieResponse {
    @SerializedName("results")
    @Expose
    private List<Movie> movies;

    public UpcomingMovieResponse() {}

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
