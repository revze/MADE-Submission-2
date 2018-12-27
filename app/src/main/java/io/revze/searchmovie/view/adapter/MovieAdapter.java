package io.revze.searchmovie.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.revze.searchmovie.R;
import io.revze.searchmovie.model.Movie;
import io.revze.searchmovie.utils.GlideApp;
import io.revze.searchmovie.view.detail.MovieDetailActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;

    private List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new MovieViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        final Movie movie = getMovies().get(position);

        GlideApp.with(context).load("https://image.tmdb.org/t/p/original/" + movie.getPoster()).into(holder.ivPoster);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvShortDescription.setText(movie.getShortDescription());
        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, MovieDetailActivity.class);
                detailIntent.putExtra(MovieDetailActivity.ID, movie.getId());
                detailIntent.putExtra(MovieDetailActivity.TITLE, movie.getTitle());
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        CardView cvMovie;
        ImageView ivPoster;
        TextView tvTitle, tvShortDescription;

        public MovieViewHolder(View itemView) {
            super(itemView);
            cvMovie = itemView.findViewById(R.id.cv_movie);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvShortDescription = itemView.findViewById(R.id.tv_short_desc);
        }
    }
}