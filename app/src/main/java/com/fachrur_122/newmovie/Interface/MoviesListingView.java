package com.fachrur_122.newmovie.Interface;

import com.fachrur_122.newmovie.view.Movie;

import java.util.List;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public interface MoviesListingView {

    void showMovies(List<Movie> movies);
    void loadingStarted();
    void loadingFailed(String errorMessage);
    void onMovieClicked(Movie movie);

}
