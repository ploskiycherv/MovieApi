package com.example.movieapi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapi.model.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieModel(
        private val repo: MovieRepo
) : ViewModel() {

    private var disposableMovie: Disposable? = null

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
        disposableMovie = repo.getMovieWithId(firstSorting)
                .subscribeOn(Schedulers.io())
                .map {
                    it.results!!.map { result ->
                        MovieItem(result.title!!,
                                result.releaseDate!!,
                                result.voteAverage!!,
                                result.posterPath!!,
                                result.id!!)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieItemLiveData.value = it
                }, {

                })
    }
}