package com.smsrn.movieshowcase.source.local

import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse
import com.smsrn.movieshowcase.source.GeneralDataSource
import com.smsrn.movieshowcase.source.LoadDataCallback

/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
class GeneralLocalDataSource : GeneralDataSource {
    override fun requestMoviesImagesCollection(movieTitle: String, callback: LoadDataCallback<PhotoDetailsResponse>) {

    }
}
