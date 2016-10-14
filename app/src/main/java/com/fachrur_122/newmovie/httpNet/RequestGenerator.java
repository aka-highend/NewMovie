package com.fachrur_122.newmovie.httpNet;

import android.support.annotation.NonNull;

import com.squareup.okhttp.Request;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class RequestGenerator {

    private static void addDefaultHeaders(@NonNull Request.Builder builder) { builder.header("Accept", "application/json"); }

    public static Request get(@NonNull String url) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        return builder.build();
    }


}
