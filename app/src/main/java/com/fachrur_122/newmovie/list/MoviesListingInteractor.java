package com.fachrur_122.newmovie.list;

import android.support.annotation.NonNull;

import com.fachrur_122.newmovie.Interface.FavoritesInteractor;
import com.fachrur_122.newmovie.apiManage.API;
import com.fachrur_122.newmovie.favorites.FavoritesStore;
import com.fachrur_122.newmovie.httpNet.RequestGenerator;
import com.fachrur_122.newmovie.httpNet.RequestHandler;
import com.fachrur_122.newmovie.sortMovies.SortingOptionStore;
import com.fachrur_122.newmovie.view.Movie;
import com.fachrur_122.newmovie.view.SortType;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 *
 * Created by fachrur_122 on 30/04/2016.
 */
public class MoviesListingInteractor implements com.fachrur_122.newmovie.Interface.MoviesListingInteractor {

    private FavoritesInteractor favoritesInteractor;

    public MoviesListingInteractor() {
        favoritesInteractor = new FavoritesInteractor() {
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
        };
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        return Observable.defer(new Func0<Observable<List<Movie>>>() {
            @Override
            public Observable<List<Movie>> call() {
                try {
                    return Observable.just(get());
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }

            private List<Movie> get() throws IOException, JSONException {
                SortingOptionStore sortingOptionStore = new SortingOptionStore();
                int selectedOption = sortingOptionStore.getSelectedOption();
                if (selectedOption == SortType.MOST_POPULAR.getValue()) {
                    return fetch(API.GET_POPULAR_MOVIES);
                } else if (selectedOption == SortType.HIGHEST_RATED.getValue()) {
                    return fetch(API.GET_HIGHEST_RATED_MOVIES);
                } else {
                    return favoritesInteractor.getFavorites();
                }
            }

            @NonNull
            private List<Movie> fetch(String url) throws IOException, JSONException {
                Request request = RequestGenerator.get(url);
                String response = RequestHandler.request(request);
                return MoviesListingParser.parse(response);
            }
        });
    }

}
