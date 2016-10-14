package com.fachrur_122.newmovie.list;

import android.support.annotation.NonNull;

import com.fachrur_122.newmovie.Interface.FavoritesInteractor;
import com.fachrur_122.newmovie.Interface.MoviesListingView;
import com.fachrur_122.newmovie.apiManage.API;
import com.fachrur_122.newmovie.httpNet.RequestGenerator;
import com.fachrur_122.newmovie.httpNet.RequestHandler;
import com.fachrur_122.newmovie.sortMovies.SortingOptionStore;
import com.fachrur_122.newmovie.view.Movie;
import com.fachrur_122.newmovie.view.SortType;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class MoviesListingPresenter implements com.fachrur_122.newmovie.Interface.MoviesListingPresenter {

    private MoviesListingView mMoviesView;
    private FavoritesInteractor favoritesInteractor;
    private com.fachrur_122.newmovie.Interface.MoviesListingInteractor mMoviesInteractor;

    public MoviesListingPresenter(MoviesListingView view) {
        mMoviesView = view;
        mMoviesInteractor = new MoviesListingInteractor() {
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
                        } else
                        {
                            return favoritesInteractor.getFavorites();
                        }
                    }

                    @NonNull
                    private List<Movie> fetch(String url) throws IOException, JSONException
                    {
                        Request request = RequestGenerator.get(url);
                        String response = RequestHandler.request(request);
                        return MoviesListingParser.parse(response);
                    }
                });
            }
        };
    }

    @Override
    public Subscription displayMovies() {
        return mMoviesInteractor.fetchMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call()
                    {
                        mMoviesView.loadingStarted();
                    }
                }).subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mMoviesView.loadingFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Movie> movies)
                    {
                        mMoviesView.showMovies(movies);
                    }
                });
    }

}
