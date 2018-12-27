package io.revze.searchmovie.view.movie.upcoming;

import android.content.Context;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.revze.searchmovie.R;
import io.revze.searchmovie.api.ApiClient;
import io.revze.searchmovie.api.ApiService;
import io.revze.searchmovie.model.UpcomingMovieResponse;
import io.revze.searchmovie.utils.Env;
import io.revze.searchmovie.view.base.Presenter;

public class UpcomingMoviePresenter implements Presenter<UpcomingMovieView> {
    private UpcomingMovieView view;
    private ApiService apiService = ApiClient.getClient();

    @Override
    public void onAttach(UpcomingMovieView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public void getMovie(final Context context) {
        if (view != null) view.showLoader();
        apiService.getUpcomingMovie(Env.TMDB_API_KEY, context.getString(R.string.api_language))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpcomingMovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpcomingMovieResponse upcomingMovieResponse) {
                        if (view != null) {
                            view.hideLoader();
                            view.onSuccessGetMovie(upcomingMovieResponse.getMovies());
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
