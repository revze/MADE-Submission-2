package io.revze.searchmovie.view.detail;

import io.revze.searchmovie.model.MovieDetailResponse;
import io.revze.searchmovie.view.base.View;

public interface MovieDetailView extends View {
    void showLoader();

    void hideLoader();

    void onSuccessGetDetail(MovieDetailResponse response);

    void onFailedGetDetail(String message);
}
