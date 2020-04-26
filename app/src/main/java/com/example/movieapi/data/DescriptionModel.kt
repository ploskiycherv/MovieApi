package com.example.movieapi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapi.api.Description
import com.example.movieapi.model.MovieDescription
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DescriptionModel(
        private val repo: MovieRepo
): ViewModel() {

    private val descriptionLiveData = MutableLiveData<List<MovieDescription>>()
    fun descriptionLiveData(): LiveData<List<MovieDescription>> {
        return descriptionLiveData
    }

    fun getDescription(id: String) {
        getListDescription(id)
    }

    private fun getListDescription(id: String) {
        repo.getDescriptionWithId(id)
                .enqueue(object : Callback<Description?>{
                    override fun onFailure(call: Call<Description?>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

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

                })
    }

}