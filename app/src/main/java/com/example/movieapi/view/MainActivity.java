package com.example.movieapi.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.movieapi.R;
import com.example.movieapi.adapter.MovieItemAdapter;
import com.example.movieapi.adapter.MovieItemDiffUtil;
import com.example.movieapi.data.MovieModel;
import com.example.movieapi.model.MovieItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView firstRecyclerView, secondRecyclerView, thirdRecyclerView;
    String firstSorting, secondSorting, thirdSorting;
    TextView firstTextView, secondTextView, thirdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstRecyclerView = findViewById(R.id.firstRecyclerView);
        secondRecyclerView = findViewById(R.id.secondRecyclerView);
        thirdRecyclerView = findViewById(R.id.thirdRecyclerView);

        firstTextView = findViewById(R.id.firstTextView);
        secondTextView = findViewById(R.id.secondTextView);
        thirdTextView = findViewById(R.id.thirdTextView);

        firstSorting = "popular";
        secondSorting = "top_rated";
        thirdSorting = "now_playing";

        firstTextView.setText(firstSorting.replace("_", " "));
        secondTextView.setText(secondSorting.replace("_", " "));
        thirdTextView.setText(thirdSorting.replace("_", " "));

        final MovieItemAdapter firstMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());
        MovieItemAdapter secondMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());
        MovieItemAdapter thirdMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());

        LinearLayoutManager firstLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager secondLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        LinearLayoutManager thirdLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        firstRecyclerView.setAdapter(firstMovieItemAdapter);
        secondRecyclerView.setAdapter(secondMovieItemAdapter);
        thirdRecyclerView.setAdapter(thirdMovieItemAdapter);

        firstRecyclerView.setLayoutManager(firstLinearLayoutManager);
        secondRecyclerView.setLayoutManager(secondLinearLayoutManager);
        thirdRecyclerView.setLayoutManager(thirdLinearLayoutManager);

        final MovieModel viewModel = new ViewModelProvider(this).get(MovieModel.class);

        viewModel.firstMovieItemLiveData().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> data) {
                firstMovieItemAdapter.submitList(data);
            }
        });

        viewModel.secondMovieItemLiveData().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> data) {
                secondMovieItemAdapter.submitList(data);
            }
        });

        viewModel.thirdMovieItemLiveData().observe(this, new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> data) {
                thirdMovieItemAdapter.submitList(data);
            }
        });

        viewModel.getMovies(firstSorting, secondSorting, thirdSorting);
    }

}