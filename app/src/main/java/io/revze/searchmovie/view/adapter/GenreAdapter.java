package io.revze.searchmovie.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.revze.searchmovie.R;
import io.revze.searchmovie.model.Genre;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> genres = new ArrayList<>();

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public GenreAdapter() {}

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_genre, parent, false);
        return new GenreViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(final GenreViewHolder holder, int position) {
        final Genre genre = getGenres().get(position);

        holder.tvName.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return getGenres().size();
    }

    class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public GenreViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}