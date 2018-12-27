package io.revze.searchmovie.api;

import io.reactivex.Observable;
import io.revze.searchmovie.model.MovieDetailResponse;
import io.revze.searchmovie.model.MovieResponse;
import io.revze.searchmovie.model.NowPlayingMovieResponse;
import io.revze.searchmovie.model.UpcomingMovieResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/movie")
    Observable<MovieResponse> search(@Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("query") String query);

    @GET("movie/{id}")
    Observable<MovieDetailResponse> getMovieDetail(@Path("id") int id,
                                                   @Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Observable<UpcomingMovieResponse> getUpcomingMovie(@Query("api_key") String apiKey,
                                                       @Query("language") String language);

    @GET("movie/now_playing")
    Observable<NowPlayingMovieResponse> getNowPlayingMovie(@Query("api_key") String apiKey,
                                                           @Query("language") String language);
}
