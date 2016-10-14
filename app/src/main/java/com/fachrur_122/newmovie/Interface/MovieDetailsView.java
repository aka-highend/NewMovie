package com.fachrur_122.newmovie.Interface;

import com.fachrur_122.newmovie.view.Movie;
import com.fachrur_122.newmovie.view.Review;
import com.fachrur_122.newmovie.view.Video;

import java.util.List;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public interface MovieDetailsView {

    void showDetails(Movie movie);
    void showTrailers(List<Video> trailers);
    void showReviews(List<Review> reviews);
    void showFavorited();
    void showUnFavorited();

}
