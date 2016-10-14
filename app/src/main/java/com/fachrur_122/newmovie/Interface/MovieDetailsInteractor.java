package com.fachrur_122.newmovie.Interface;

import com.fachrur_122.newmovie.view.Review;
import com.fachrur_122.newmovie.view.Video;

import java.util.List;

import rx.Observable;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public interface MovieDetailsInteractor {

    Observable<List<Video>> getTrailers(String id);
    Observable<List<Review>> getReviews(String id);

}
