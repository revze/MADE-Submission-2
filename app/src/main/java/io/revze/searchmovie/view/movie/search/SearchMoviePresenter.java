package io.revze.searchmovie.view.movie.search;

import android.content.Context;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.revze.searchmovie.R;
import io.revze.searchmovie.api.ApiClient;
import io.revze.searchmovie.api.ApiService;
import io.revze.searchmovie.model.MovieResponse;
import io.revze.searchmovie.utils.Env;
import io.revze.searchmovie.view.base.Presenter;

public class SearchMoviePresenter implements Presenter<SearchMovieView> {
    private SearchMovieView view;
    private ApiService apiService = ApiClient.getClient();

    @Override
    public void onAttach(SearchMovieView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public void searchMovie(final Context context, String query) {
        if (view != null) view.showLoader();
        apiService.search(Env.TMDB_API_KEY, context.getString(R.string.api_language), query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        if (view != null) {
                            view.hideLoader();
                            view.onSuccessGetMovie(movieResponse.getMovies());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.hideLoader();
                            if (e instanceof IOException)
                                view.onFailedGetMovie(context.getString(R.string.network_error));
                            else view.onFailedGetMovie(context.getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
