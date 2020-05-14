package com.example.movieapi.presentation.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapi.domain.interactor.MovieInteractor
import com.example.movieapi.presentation.description.item.MovieDescriptionUiItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DescriptionModel(
        private val interactor: MovieInteractor
) : ViewModel() {

    private var disposableDescription: Disposable? = null

    private val descriptionLiveData = MutableLiveData<MovieDescriptionUiItem>()
    fun descriptionLiveData(): LiveData<MovieDescriptionUiItem> {
        return descriptionLiveData
    }

    fun getDescription(id: String) {
        getListDescription(id)
    }

    private fun getListDescription(id: String) {
        disposableDescription = interactor.getDescription(id)
                .map {
                    val runtime = it.runtime
                    val hours = runtime / 60
                    val minutes = runtime % 60
                    val time = "$hours hr $minutes min"
                    MovieDescriptionUiItem(it.title,
                            it.releaseDate.substring(0, 4),
                            it.voteAverage,
                            it.posterPath,
                            it.backdropPath,
                            it.id,
                            it.overview,
                            time)
                }
                .subscribeOn(Schedulers.io())
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