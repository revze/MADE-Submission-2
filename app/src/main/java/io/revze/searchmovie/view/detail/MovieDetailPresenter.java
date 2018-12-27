package io.revze.searchmovie.view.detail;

import android.content.Context;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.revze.searchmovie.R;
import io.revze.searchmovie.api.ApiClient;
import io.revze.searchmovie.api.ApiService;
import io.revze.searchmovie.model.MovieDetailResponse;
import io.revze.searchmovie.utils.Env;
import io.revze.searchmovie.view.base.Presenter;

public class MovieDetailPresenter implements Presenter<MovieDetailView> {
    private MovieDetailView view;
    private ApiService apiService = ApiClient.getClient();

    @Override
    public void onAttach(MovieDetailView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public void getMovieDetail(final Context context, int id) {
        if (view != null) view.showLoader();
        apiService.getMovieDetail(id, Env.TMDB_API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetailResponse movieDetailResponse) {
                        if (view != null) {
                            view.hideLoader();
                            view.onSuccessGetDetail(movieDetailResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view != null) {
                            view.hideLoader();
                            if (e instanceof IOException) view.onFailedGetDetail(context.getString(R.string.network_error));
                            else view.onFailedGetDetail(context.getString(R.string.server_error));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
