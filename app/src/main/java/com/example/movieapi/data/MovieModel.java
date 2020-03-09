package com.example.movieapi.data;

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
    private MutableLiveData<List<MovieItem>> movieItemLiveData = new MutableLiveData<>();

    public LiveData<List<MovieItem>> movieItemLiveData() {
        return movieItemLiveData;
    }

    public void getMovies(String sorting) {

        movieRepo.getMovieWithId(sorting)
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
                                    result.getPosterPath()));

                        }
                        movieItemLiveData.setValue(movieItems);
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {

                    }
                });

    }


}
