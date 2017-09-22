package com.mosoti.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

public class MoviesActivity extends AppCompatActivity {
    public ArrayList<Movie> mMovie = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Intent intent = getIntent();
        String serchterm = intent.getStringExtra("serchterm");

        getMovie(serchterm);
    }

    public void getMovie(String serchterm) {
        final MovieService movieService = new MovieService();
        movieService.findMovie(serchterm, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                String jsonData = response.body().string();
//                 Log.v("data", jsonData);

                mMovie=movieService.processResults(response);

                MoviesActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                for (Movie movie :mMovie){
                    Log.v("image",movie.getImage());
                    Log.v("tittle",movie.getTittle());
                    Log.v("overview",movie.getOverview());
                    Log.v("type",movie.getType());
                    Log.v("release",movie.getReleaseDate());
//                    Log.v("image",movie.getRating());
                }


                }

            });

            }

        });
    }
}
