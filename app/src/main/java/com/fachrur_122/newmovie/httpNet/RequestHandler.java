package com.fachrur_122.newmovie.httpNet;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class RequestHandler {

    public static String request(Request request) throws IOException {
        Log.i("HTTP", request.method() + " : " + request.urlString());
        OkHttpClient httpClient = HttpClient.getClient();
        Response response = httpClient.newCall(request).execute();
        String body = response.body().string();
        Log.i("HTTP", response.code() + " : " + body);

        if (response.isSuccessful()) { return body; }
        else { throw new RuntimeException(response.message()); }
    }

}
