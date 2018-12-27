package io.revze.searchmovie.view.movie.upcoming;

import java.util.List;

import io.revze.searchmovie.model.Movie;
import io.revze.searchmovie.view.base.View;

public interface UpcomingMovieView extends View {
    void showLoader();

    void hideLoader();

    void onSuccessGetMovie(List<Movie> movies);

    void onFailedGetMovie(String message);
}
