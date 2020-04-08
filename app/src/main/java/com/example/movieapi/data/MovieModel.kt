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
import java.util.*

class MovieModel : ViewModel() {
    private val movieRepo: MovieRepo = MovieRepoImpl()

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

    private val descriptionLiveData = MutableLiveData<List<MovieDescription>>()
    fun descriptionLiveData(): LiveData<List<MovieDescription>> {
        return descriptionLiveData
    }

    fun getMovies(firstSorting: String, secondSorting: String, thirdSorting: String) {
        getListMovie(firstSorting, firstMovieItemLiveData)
        getListMovie(secondSorting, secondMovieItemLiveData)
        getListMovie(thirdSorting, thirdMovieItemLiveData)
    }

    fun getDescriptions(id: String) {
        getDescription(id)
    }

    private fun getDescription(id: String) {
        movieRepo.getDescriptionWithId(id)
                ?.enqueue(object : Callback<Description?> {
                    override fun onResponse(call: Call<Description?>, response: Response<Description?>) {
                        val description = response.body()
                        val runtime = description!!.runtime
                        val hours = runtime / 60
                        val minutes = runtime % 60
                        val time = "$hours hr $minutes min"
                        val movieDescriptions: MutableList<MovieDescription> = ArrayList()
                        movieDescriptions.add(MovieDescription(description.title!!, description.releaseDate!!.substring(0, 4), description.voteAverage!!, description.posterPath!!, description.backdropPath!!, description.id.toString(), description.overview!!, time))
                        descriptionLiveData.value = movieDescriptions
                    }

                    override fun onFailure(call: Call<Description?>, t: Throwable) {}
                })
    }

    private fun getListMovie(firstSorting: String, movieItemLiveData: MutableLiveData<List<MovieItem>>) {
        movieRepo.getMovieWithId(firstSorting)
                ?.enqueue(object : Callback<MovieList?> {
                    override fun onResponse(call: Call<MovieList?>, response: Response<MovieList?>) {
                        val movieList = response.body()
                        val movieItems: MutableList<MovieItem> = ArrayList()
                        for (i in movieList!!.results!!.indices) {
                            val result = movieList.results!![i]
                            movieItems.add(MovieItem(result.title!!,
                                    result.releaseDate!!,
                                    result.voteAverage!!,
                                    result.posterPath!!,
                                    result.id!!))
                        }
                        movieItemLiveData.value = movieItems
                    }

                    override fun onFailure(call: Call<MovieList?>, t: Throwable) {}
                })
    }
}