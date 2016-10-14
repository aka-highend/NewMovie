package com.fachrur_122.newmovie.details;

import com.fachrur_122.newmovie.apiManage.API;
import com.fachrur_122.newmovie.httpNet.RequestGenerator;
import com.fachrur_122.newmovie.httpNet.RequestHandler;
import com.fachrur_122.newmovie.view.Review;
import com.fachrur_122.newmovie.view.Video;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class MovieDetailsInteractor implements com.fachrur_122.newmovie.Interface.MovieDetailsInteractor {

    @Override
    public Observable<List<Video>> getTrailers(final String id) {
        return Observable.defer(new Func0<Observable<List<Video>>>() {
            @Override
            public Observable<List<Video>> call() {
                try {
                    return Observable.just(get(id));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }

            private List<Video> get(String id) throws IOException, JSONException
            {
                String url = String.format(API.GET_TRAILERS, id);
                Request request = RequestGenerator.get(url);
                String body = RequestHandler.request(request);
                return MovieDetailsParser.parseTrailers(body);
            }
        });
    }

    @Override
    public Observable<List<Review>> getReviews(final String id) {
        return Observable.defer(new Func0<Observable<List<Review>>>() {
            @Override
            public Observable<List<Review>> call() {
                try {
                    return Observable.just(get(id));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }

            private List<Review> get(String id) throws IOException, JSONException
            {
                String url = String.format(API.GET_REVIEWS, id);
                Request request = RequestGenerator.get(url);
                String body = RequestHandler.request(request);
                return MovieDetailsParser.parseReviews(body);
            }
        });
    }

}
