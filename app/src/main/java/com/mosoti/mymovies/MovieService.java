package com.mosoti.mymovies;



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mosoti on 9/14/17.
 */

public class MovieService {

public static void findMovie(String serchterm, Callback callback){
    OkHttpClient client = new OkHttpClient.Builder()
            .build();

    HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_BASE_URL).newBuilder();
    urlBuilder.addQueryParameter(Constants.MOVIE_API_KEY_HOLDER,Constants.MOVIE_API_KEY);
    urlBuilder.addQueryParameter(Constants.MOVIE_QUERY_PARAMETER , serchterm);

    String url = urlBuilder.build().toString();

    Request request=new Request.Builder()
            .url(url)
            .build();

    Call call = client.newCall(request);

    call.enqueue(callback);

}

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = null;
                movieJSON= (JSONObject) new JSONObject(jsonData);
                JSONArray listJSON = movieJSON.getJSONArray("results");

                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject itemJSON = listJSON.getJSONObject(i);
                    String type= itemJSON.getString("media_type");
                    String tittle="";
                    String releasedate="";
                    if (itemJSON.getString("media_type").equals("tv")){
                        tittle= itemJSON.getString("name");
                        releasedate= itemJSON.getString("first_air_date");
                    }else{
                         tittle= itemJSON.getString("title");
                        releasedate= itemJSON.getString("release_date");
                    }
                    String image= "https://image.tmdb.org/t/p/w500"+itemJSON.getString("poster_path");
                    String overview= itemJSON.getString("overview");

                    double rating= itemJSON.getDouble("vote_average");

    Movie movie =new Movie(type,tittle,image,overview,releasedate,rating);
                    movies.add(movie);
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
            Log.v("stck","stacktrace");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("stck","stacktrace e");
        }
        return movies;
    }
}
