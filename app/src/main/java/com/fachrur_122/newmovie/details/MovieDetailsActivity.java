package com.fachrur_122.newmovie.details;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.fachrur_122.newmovie.R;
import com.fachrur_122.newmovie.apiManage.Const;
import com.fachrur_122.newmovie.view.Movie;

/**
 * Created by fachrur_122 on 30/04/2016.
 *
 */
public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Const.MOVIE)) {
                Movie movie = extras.getParcelable(Const.MOVIE);
                if (movie != null) {
                    MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.getInstance(movie);
                    getSupportFragmentManager().beginTransaction().add(R.id.movie_details_container, movieDetailsFragment).commit();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        } return super.onOptionsItemSelected(item);
    }

}
