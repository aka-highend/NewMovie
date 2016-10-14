package com.fachrur_122.newmovie.Interface;

import com.fachrur_122.newmovie.view.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public interface MoviesListingInteractor {

    Observable<List<Movie>> fetchMovies();

}
