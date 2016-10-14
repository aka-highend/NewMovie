package com.fachrur_122.newmovie.Interface;


import com.fachrur_122.newmovie.view.Movie;

import rx.Subscription;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public interface MovieDetailsPresenter {

    void showDetails(Movie movie);
    Subscription showTrailers(Movie movie);
    Subscription showReviews(Movie movie);
    void showFavoriteButton(Movie movie);
    void onFavoriteClick(Movie movie);

}
