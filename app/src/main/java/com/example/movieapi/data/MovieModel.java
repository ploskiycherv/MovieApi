package com.example.movieapi.data;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapi.api.MovieList;
import com.example.movieapi.api.Result;
import com.example.movieapi.model.MovieItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieModel extends ViewModel {

    private MovieRepo movieRepo = new MovieRepoImpl();

    private MutableLiveData<List<MovieItem>> firstMovieItemLiveData = new MutableLiveData<>();

    public LiveData<List<MovieItem>> firstMovieItemLiveData() {
        return firstMovieItemLiveData;
    }

    private MutableLiveData<List<MovieItem>> secondMovieItemLiveData = new MutableLiveData<>();

    public LiveData<List<MovieItem>> secondMovieItemLiveData() {
        return secondMovieItemLiveData;
    }

    private MutableLiveData<List<MovieItem>> thirdMovieItemLiveData = new MutableLiveData<>();

    public LiveData<List<MovieItem>> thirdMovieItemLiveData() {
        return thirdMovieItemLiveData;
    }


    public void getMovies(String firstSorting, String secondSorting, String thirdSorting) {

        getListMovie(firstSorting, firstMovieItemLiveData);
        getListMovie(secondSorting, secondMovieItemLiveData);
        getListMovie(thirdSorting, thirdMovieItemLiveData);

    }

    private void getListMovie(String firstSorting, MutableLiveData<List<MovieItem>> movieItemLiveData) {
        movieRepo.getMovieWithId(firstSorting)
                .enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        MovieList movieList = response.body();

                        List<MovieItem> movieItems = new ArrayList<MovieItem>();

                        for (int i = 0; i < movieList.getResults().size(); i++) {

                            Result result = movieList.getResults().get(i);

                            movieItems.add(new MovieItem(result.getTitle(),
                                    result.getReleaseDate(),
                                    result.getVoteAverage(),
                                    result.getPosterPath(),
                                    result.getId()));

                        }

                        movieItemLiveData.setValue(movieItems);
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {

                    }
                });
    }


}
