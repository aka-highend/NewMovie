package com.fachrur_122.newmovie.details;

import com.fachrur_122.newmovie.Interface.FavoritesInteractor;
import com.fachrur_122.newmovie.Interface.MovieDetailsView;
import com.fachrur_122.newmovie.favorites.FavoritesStore;
import com.fachrur_122.newmovie.view.Movie;
import com.fachrur_122.newmovie.view.Review;
import com.fachrur_122.newmovie.view.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class MovieDetailsPresenter implements com.fachrur_122.newmovie.Interface.MovieDetailsPresenter {

    private MovieDetailsView mMovieDetailsView;
    private MovieDetailsInteractor mMovieDetailsInteractor;
    private FavoritesInteractor mFavoritesInteractor;

    public MovieDetailsPresenter(MovieDetailsView movieDetailsView) {
        mMovieDetailsView = movieDetailsView;
        mMovieDetailsInteractor = new MovieDetailsInteractor();
        mFavoritesInteractor = new FavoritesInteractor() {
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
    public void showDetails(Movie movie) { mMovieDetailsView.showDetails(movie); }

    @Override
    public Subscription showTrailers(Movie movie) {
        return mMovieDetailsInteractor.getTrailers(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Video>>() {
                    @Override
                    public void onCompleted() {  }

                    @Override
                    public void onError(Throwable e) {  }

                    @Override
                    public void onNext(List<Video> videos) { mMovieDetailsView.showTrailers(videos); }
                });
    }

    @Override
    public Subscription showReviews(Movie movie) {
        return mMovieDetailsInteractor.getReviews(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Review>>() {
                    @Override
                    public void onCompleted() {  }

                    @Override
                    public void onError(Throwable e) {  }

                    @Override
                    public void onNext(List<Review> reviews) { mMovieDetailsView.showReviews(reviews); }
                });
    }

    @Override
    public void showFavoriteButton(Movie movie) {
        boolean isFavorite = mFavoritesInteractor.isFavorite(movie.getId());
        if(isFavorite) { mMovieDetailsView.showFavorited(); }
        else { mMovieDetailsView.showUnFavorited(); }
    }

    @Override
    public void onFavoriteClick(Movie movie) {
        boolean isFavorite = mFavoritesInteractor.isFavorite(movie.getId());
        if(isFavorite) {
            mFavoritesInteractor.unFavorite(movie.getId());
            mMovieDetailsView.showUnFavorited();
        } else {
            mFavoritesInteractor.setFavorite(movie);
            mMovieDetailsView.showFavorited();
        }
    }

}
