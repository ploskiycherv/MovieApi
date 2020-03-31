package com.example.movieapi.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMovieListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MainMovieListFragment extends Fragment {

    RecyclerView firstRecyclerView, secondRecyclerView, thirdRecyclerView;
    String firstSorting, secondSorting, thirdSorting;
    TextView firstTextView, secondTextView, thirdTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMovieListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMovieListFragment newInstance(String param1, String param2) {
        MainMovieListFragment fragment = new MainMovieListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public MainMovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_movie_list, container, false);

        firstRecyclerView = getView().findViewById(R.id.firstRecyclerView);
        secondRecyclerView = getView().findViewById(R.id.secondRecyclerView);
        thirdRecyclerView = getView().findViewById(R.id.thirdRecyclerView);

        firstTextView = getView().findViewById(R.id.firstTextView);
        secondTextView = getView().findViewById(R.id.secondTextView);
        thirdTextView = getView().findViewById(R.id.thirdTextView);

        firstSorting = "popular";
        secondSorting = "top_rated";
        thirdSorting = "now_playing";

        firstTextView.setText(firstSorting.replace("_", " "));
        secondTextView.setText(secondSorting.replace("_", " "));
        thirdTextView.setText(thirdSorting.replace("_", " "));

        final MovieItemAdapter firstMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());
        MovieItemAdapter secondMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());
        MovieItemAdapter thirdMovieItemAdapter = new MovieItemAdapter(new MovieItemDiffUtil());

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
}
