package com.example.movieapi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapi.api.Description
import com.example.movieapi.api.MovieList
import com.example.movieapi.model.MovieDescription
import com.example.movieapi.model.MovieItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*

class MovieModel(
        private val repo: MovieRepo
) : ViewModel() {

    private val firstSorting = "popular"
    private val secondSorting = "top_rated"
    private val thirdSorting = "now_playing"

    private val firstMovieItemLiveData = MutableLiveData<List<MovieItem>>()
    fun firstMovieItemLiveData(): LiveData<List<MovieItem>> {
        return firstMovieItemLiveData
    }

    private val secondMovieItemLiveData = MutableLiveData<List<MovieItem>>()
    fun secondMovieItemLiveData(): LiveData<List<MovieItem>> {
        return secondMovieItemLiveData
    }

    private val thirdMovieItemLiveData = MutableLiveData<List<MovieItem>>()
    fun thirdMovieItemLiveData(): LiveData<List<MovieItem>> {
        return thirdMovieItemLiveData
    }

    fun getMovies() {
        Timber.d("Вызов функции полученмя фильмов")
        getListMovie(firstSorting, firstMovieItemLiveData)
        getListMovie(secondSorting, secondMovieItemLiveData)
        getListMovie(thirdSorting, thirdMovieItemLiveData)
    }

    private fun getListMovie(firstSorting: String, movieItemLiveData: MutableLiveData<List<MovieItem>>) {
        Timber.d("Сортинг: $firstSorting")
        repo.getMovieWithId(firstSorting)
                .enqueue(object : Callback<MovieList?> {
                    override fun onResponse(call: Call<MovieList?>, response: Response<MovieList?>) {

                        movieItemLiveData.value = response.body()!!.results!!
                                .map {
                                    MovieItem(it.title!!,
                                            it.releaseDate!!,
                                            it.voteAverage!!,
                                            it.posterPath!!,
                                            it.id!!)
                                }

                    }

                    override fun onFailure(call: Call<MovieList?>, t: Throwable) {}
                })
    }
}