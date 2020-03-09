package com.example.movieapi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapi.R;
import com.example.movieapi.adapter.MovieItemAdapter;
import com.example.movieapi.adapter.MovieItemDiffUtil;
import com.example.movieapi.api.MovieList;
import com.example.movieapi.api.NetworkService;
import com.example.movieapi.api.Result;
import com.example.movieapi.data.MovieModel;
import com.example.movieapi.model.MovieItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView firstRecyclerView, secondRecyclerView, thirdRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieItemAdapter firstMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());
        MovieItemAdapter secondMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());
        MovieItemAdapter thirdMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());

        LinearLayoutManager firstLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager secondLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager thirdLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        firstRecyclerView = findViewById(R.id.firstRecyclerView);
        secondRecyclerView = findViewById(R.id.secondRecyclerView);

        firstRecyclerView.setAdapter(firstMovieItemAdapter);
        secondRecyclerView.setAdapter(secondMovieItemAdapter);

        firstRecyclerView.setLayoutManager(firstLinearLayoutManager);
        secondRecyclerView.setLayoutManager(secondLinearLayoutManager);

        final MovieModel viewModel = new ViewModelProvider(this).get(MovieModel.class);
        viewModel.movieItemLiveData().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> data) {
                firstMovieItemAdapter.submitList(data);
            }
        });

        viewModel.getMovies("popular");
    }

}

