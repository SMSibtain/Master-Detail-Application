package com.smsrn.movieshowcase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.models.response.PhotoDetails
import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse
import com.smsrn.movieshowcase.source.GeneralRepository
import com.smsrn.movieshowcase.source.LoadDataCallback
import com.smsrn.movieshowcase.util.Injection


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
class MoviesDetailActivityViewModel : ViewModel() {

    private val generalRepository: GeneralRepository = Injection.provideGeneralRepository()

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: MutableLiveData<MovieDetails>
        get() = _movieDetails

    private val _items= MutableLiveData<ArrayList<PhotoDetails>>().apply { value = ArrayList() }
    val items: LiveData<ArrayList<PhotoDetails>>
        get() = _items

    fun requestMovieImages() {
        _movieDetails.value?.title?.let {
            generalRepository.requestMoviesImagesCollection(it, object : LoadDataCallback<PhotoDetailsResponse> {
                override fun onDataLoaded(response: PhotoDetailsResponse) {
                    _items.value = response.photo
                }

                override fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {

                }
            })
        }
    }
}