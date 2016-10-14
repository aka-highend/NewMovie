package com.fachrur_122.newmovie.Interface;

import com.fachrur_122.newmovie.view.Movie;

import java.util.List;

/**
 * Created by fachrur_122 on 30/04/2016.
 */
public interface FavoritesInteractor {

    void setFavorite(Movie movie);
    boolean isFavorite(String id);
    List<Movie> getFavorites();
    void unFavorite(String id);

}
