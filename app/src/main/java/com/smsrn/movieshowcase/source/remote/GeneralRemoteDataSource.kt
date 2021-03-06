package com.smsrn.movieshowcase.source.remote

import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse
import com.smsrn.movieshowcase.source.GeneralDataSource
import com.smsrn.movieshowcase.source.LoadDataCallback
import com.smsrn.movieshowcase.util.Constants.FLICKR_API_KEY


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
class GeneralRemoteDataSource : GeneralDataSource {
    override fun requestMoviesImagesCollection(
        movieTitle: String,
        page: Int,
        callback: LoadDataCallback<PhotoDetailsResponse>
    ) {
        Backend.flicker.getMovieImageCollectionFlickr(FLICKR_API_KEY, movieTitle, page)
            .enqueue(GenericNetworkCallback(callback))
    }
}