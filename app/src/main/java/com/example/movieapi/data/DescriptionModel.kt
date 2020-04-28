package com.example.movieapi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapi.model.MovieDescription
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class DescriptionModel(
        private val repo: MovieRepo
) : ViewModel() {

    private var disposableDescription: Disposable? = null

    private val descriptionLiveData = MutableLiveData<MovieDescription>()
    fun descriptionLiveData(): LiveData<MovieDescription> {
        return descriptionLiveData
    }

    fun getDescription(id: String) {
        getListDescription(id)
    }

    private fun getListDescription(id: String) {
        disposableDescription = repo.getDescriptionWithId(id)
                .subscribeOn(Schedulers.io())
                .map {
                    val runtime = it.runtime
                    val hours = runtime / 60
                    val minutes = runtime % 60
                    val time = "$hours hr $minutes min"
                    MovieDescription(it.title!!, it.releaseDate!!.substring(0, 4), it.voteAverage!!, it.posterPath!!, it.backdropPath!!, it.id.toString(), it.overview!!, time)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    descriptionLiveData.value = it
                }, {
                    TODO()
                })
    }

    override fun onCleared() {
        disposableDescription?.dispose()
    }

}