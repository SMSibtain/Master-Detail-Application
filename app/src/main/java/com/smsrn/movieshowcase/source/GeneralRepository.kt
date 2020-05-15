package com.smsrn.movieshowcase.source

import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse
import com.smsrn.movieshowcase.source.local.GeneralLocalDataSource
import com.smsrn.movieshowcase.source.remote.GeneralRemoteDataSource


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */

class GeneralRepository(
    private val generalLocalDataSource: GeneralLocalDataSource,
    private var generalRemoteDataSource: GeneralRemoteDataSource
) : GeneralDataSource {
    override fun requestMoviesImagesCollection(
        movieTitle: String,
        page: Int,
        callback: LoadDataCallback<PhotoDetailsResponse>
    ) {
        generalRemoteDataSource.requestMoviesImagesCollection(movieTitle, page, callback)
    }
}