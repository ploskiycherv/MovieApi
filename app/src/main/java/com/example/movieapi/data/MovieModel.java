package com.example.movieapi.data;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapi.R;
import com.example.movieapi.api.Description;
import com.example.movieapi.api.MovieList;
import com.example.movieapi.api.Result;
import com.example.movieapi.model.MovieDescription;
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

    private MutableLiveData<List<MovieDescription>> descriptionLiveData = new MutableLiveData<>();

    public LiveData<List<MovieDescription>> descriptionLiveData() {
        return descriptionLiveData;
    }

    public void getMovies(String firstSorting, String secondSorting, String thirdSorting) {

        getListMovie(firstSorting, firstMovieItemLiveData);
        getListMovie(secondSorting, secondMovieItemLiveData);
        getListMovie(thirdSorting, thirdMovieItemLiveData);

    }

    public void getDescriptions(String id) {
        getDescription(id);
    }

    private void getDescription(String id) {
        movieRepo.getDescriptionWithId(id)
                .enqueue(new Callback<Description>() {
                    @Override
                    public void onResponse(Call<Description> call, Response<Description> response) {

                        Description description = response.body();

                        int runtime = description.getRuntime();

                        int hours = runtime / 60;

                        int minutes = runtime % 60;

                        String time = Integer.toString(hours) + " hr " + Integer.toString(minutes) + " min";

                        List<MovieDescription> movieDescriptions = new ArrayList<>();
                        movieDescriptions.add(new MovieDescription(description.getTitle(), description.getReleaseDate().substring(0, 4), description.getVoteAverage(), description.getPosterPath(), description.getBackdropPath(), description.getId().toString(), description.getOverview(), time));
                        descriptionLiveData.setValue(movieDescriptions);
                    }

                    @Override
                    public void onFailure(Call<Description> call, Throwable t) {

                    }
                });
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
