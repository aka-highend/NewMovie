package com.fachrur_122.newmovie.favorites;

import com.fachrur_122.newmovie.view.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class FavoritesInteractor implements com.fachrur_122.newmovie.Interface.FavoritesInteractor {

    @Override
    public void setFavorite(Movie movie) {
        FavoritesStore favoritesStore = new FavoritesStore();
        favoritesStore.setFavorite(movie);
    }

    @Override
    public boolean isFavorite(String id) {
        FavoritesStore favoritesStore = new FavoritesStore();
        return favoritesStore.isFavorite(id);
    }

    @Override
    public List<Movie> getFavorites() {
        try {
            FavoritesStore favoritesStore = new FavoritesStore();
            return favoritesStore.getFavorites();
        } catch (IOException ignored) {
            return new ArrayList<>(0);
        }
    }

    @Override
    public void unFavorite(String id) {
        FavoritesStore favoritesStore = new FavoritesStore();
        favoritesStore.unfavorite(id);
    }

}
