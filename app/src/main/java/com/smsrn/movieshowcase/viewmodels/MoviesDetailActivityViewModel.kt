package com.smsrn.movieshowcase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.models.response.PhotoDetails
import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse
import com.smsrn.movieshowcase.source.GeneralRepository
import com.smsrn.movieshowcase.source.LoadDataCallback
import com.smsrn.movieshowcase.util.AppToast
import com.smsrn.movieshowcase.util.Constants.DIGIT_ONE
import com.smsrn.movieshowcase.util.Constants.DIGIT_ZERO
import com.smsrn.movieshowcase.util.DialogUtils
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

    private val _items = MutableLiveData<ArrayList<PhotoDetails>>().apply { value = ArrayList() }
    val items: LiveData<ArrayList<PhotoDetails>>
        get() = _items

    var pageCount: Int = DIGIT_ONE
    var totalPageCount: Int = DIGIT_ONE
    var isFetchingPhotos: Boolean = false
    var isAllImagesFetched: Boolean = false

    fun requestMovieImages() {
        isFetchingPhotos = true
        _movieDetails.value?.title?.let {
            generalRepository.requestMoviesImagesCollection(it, pageCount,
                object : LoadDataCallback<PhotoDetailsResponse> {
                    override fun onDataLoaded(response: PhotoDetailsResponse) {
                        if (response.photos.page == DIGIT_ONE) {
                            _items.value = response.photos.photo
                        } else {
                            _items.value?.addAll(response.photos.photo)
                            _items.value = _items.value
                        }
                        totalPageCount = response.photos.pages
                        if (totalPageCount == DIGIT_ZERO ||
                            pageCount == totalPageCount
                        ) {
                            isAllImagesFetched = true
                        }
                        pageCount += 1
                        isFetchingPhotos = false
                        DialogUtils.hideProgressDialog()
                    }

                    override fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {
                        DialogUtils.hideProgressDialog()
                        isFetchingPhotos = false
                        AppToast.showDebugToast(reasonMsg)
                    }
                })
        }
    }
}