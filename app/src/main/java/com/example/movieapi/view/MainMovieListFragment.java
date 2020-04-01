package com.example.movieapi.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieapi.R;
import com.example.movieapi.adapter.MovieItemAdapter;
import com.example.movieapi.adapter.MovieItemDiffUtil;
import com.example.movieapi.data.MovieModel;
import com.example.movieapi.model.MovieItem;

import java.util.List;

public class MainMovieListFragment extends Fragment {

    RecyclerView firstRecyclerView, secondRecyclerView, thirdRecyclerView;
    String firstSorting, secondSorting, thirdSorting;
    TextView firstTextView, secondTextView, thirdTextView;
    FragmentTransaction fragmentTransaction;

    public MainMovieListFragment(FragmentTransaction fragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_movie_list, null);

        firstRecyclerView = view.findViewById(R.id.firstRecyclerView);
        secondRecyclerView = view.findViewById(R.id.secondRecyclerView);
        thirdRecyclerView = view.findViewById(R.id.thirdRecyclerView);

        firstTextView = view.findViewById(R.id.firstTextView);
        secondTextView = view.findViewById(R.id.secondTextView);
        thirdTextView = view.findViewById(R.id.thirdTextView);

        firstSorting = "popular";
        secondSorting = "top_rated";
        thirdSorting = "now_playing";

        firstTextView.setText(firstSorting.replace("_", " "));
        secondTextView.setText(secondSorting.replace("_", " "));
        thirdTextView.setText(thirdSorting.replace("_", " "));

        MovieItemAdapter firstMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil(), onClickListener);
        MovieItemAdapter secondMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil(), onClickListener);
        MovieItemAdapter thirdMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil(), onClickListener);

        LinearLayoutManager firstLinearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager secondLinearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager thirdLinearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);

        firstRecyclerView.setAdapter(firstMovieItemAdapter);
        secondRecyclerView.setAdapter(secondMovieItemAdapter);
        thirdRecyclerView.setAdapter(thirdMovieItemAdapter);

        firstRecyclerView.setLayoutManager(firstLinearLayoutManager);
        secondRecyclerView.setLayoutManager(secondLinearLayoutManager);
        thirdRecyclerView.setLayoutManager(thirdLinearLayoutManager);

        final MovieModel viewModel = new ViewModelProvider(this).get(MovieModel.class);

        viewModel.firstMovieItemLiveData().observe(getViewLifecycleOwner(), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> data) {
                firstMovieItemAdapter.submitList(data);
            }
        });

        viewModel.secondMovieItemLiveData().observe(getViewLifecycleOwner(), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> data) {
                secondMovieItemAdapter.submitList(data);
            }
        });

        viewModel.thirdMovieItemLiveData().observe(getViewLifecycleOwner(), new Observer<List<MovieItem>>() {
            @Override
            public void onChanged(@Nullable List<MovieItem> data) {
                thirdMovieItemAdapter.submitList(data);
            }
        });

        viewModel.getMovies(firstSorting, secondSorting, thirdSorting);

        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentTransaction.replace(R.id.main_container, new DescriptionMovieFragment());
        }
    };

}
