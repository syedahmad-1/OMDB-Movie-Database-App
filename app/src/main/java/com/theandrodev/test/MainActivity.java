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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static Retrofit retrofit = null;


    private ApiInterface apiInterface;
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
    String QueryString;
    Button searchButton;
    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        movieTitle = findViewById(R.id.tv_movietitle);
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
        searchEditText = findViewById(R.id.et_searchView);
        searchButton = findViewById(R.id.btn_searchButton);

        searchEditText.setInputType(InputType.TYPE_CLASS_TEXT);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie_name = searchEditText.getText().toString();
                fetchMovies(movie_name, APIKEY);

            }
        });









    }

    private void fetchMovies(String movie_name, String APIKEY){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface =retrofit.create(ApiInterface.class);
        Call<PostPojo> call = apiInterface.getMovie(movie_name, APIKEY, "movie");
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
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });



    }




}