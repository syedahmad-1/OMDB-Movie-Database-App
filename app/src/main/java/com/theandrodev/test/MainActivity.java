package com.theandrodev.test;

import static android.content.ContentValues.TAG;
import static com.theandrodev.test.RetrofitInstance.APIKEY;
import static com.theandrodev.test.RetrofitInstance.BASEURL;
import static com.theandrodev.test.RetrofitInstance.getRetrofit;
import static com.theandrodev.test.RetrofitInstance.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.icu.text.CaseMap;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.androidgamesdk.gametextinput.Listener;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static Retrofit retrofit = null;


    ApiInterface apiInterface;
    TextView movieTitle;
    SearchView searchView;
    TextView duration;
    TextView releasedYear;
    TextView imdbRating;
    TextView genre;
    TextView actors;
    TextView plot;
    ImageView moviePoster;
    private static TextView initialText;
    String titleName;
    public static Boolean searchActivityIsChanged = false;
    public static String movie_name="";
    LinearLayout linearLayout;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        movieTitle = findViewById(R.id.tv_movietitle);
        searchView = findViewById(R.id.search_view);
        duration = findViewById(R.id.tv_duration);
        releasedYear = findViewById(R.id.tv_year);
        imdbRating = findViewById(R.id.tv_rating);
        actors = findViewById(R.id.tv_actors);
        genre = findViewById(R.id.tv_genreString);
        plot = findViewById(R.id.tv_story);
        initialText = findViewById(R.id.tv_initialText);
        moviePoster = findViewById(R.id.iv_poster);
        linearLayout = findViewById(R.id.ll_movie);
        scrollView = findViewById(R.id.sv);


            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//
                searchActivityIsChanged = true;
                return true;

            }
        });





        String mTitle ="";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface =retrofit.create(ApiInterface.class);
        Call<PostPojo> call = apiInterface.getMovieDetails(movie_name);
        call.enqueue(new Callback<PostPojo>() {
            @Override
            public void onResponse(Call<PostPojo> call, Response<PostPojo> response) {
                if(response.code()!=200)
                {
                    return;
                }
                PostPojo postPojo = response.body();
                String response_text="";
                response_text=postPojo.getTitle();
                movieTitle.setText(response_text);

                response_text=postPojo.getRuntime();
                duration.setText(response_text);

                response_text = postPojo.getYear();
                releasedYear.setText(response_text);

                response_text = postPojo.getImdbRating();
                imdbRating.setText(response_text);

                response_text = postPojo.getGenre();
                genre.setText(response_text);

                response_text = postPojo.getActors();
                actors.setText(response_text);

                response_text = postPojo.getPlot();
                plot.setText(response_text);

                response_text = postPojo.getPoster();
                Picasso.get().load(response_text).into(moviePoster);
            }

            @Override
            public void onFailure(Call<PostPojo> call, Throwable t) {




            }
        });





    }

    private void fetchMovies(String movie_name){


    }




}