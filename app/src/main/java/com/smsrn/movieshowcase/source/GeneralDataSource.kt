package com.smsrn.movieshowcase.source

import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
interface GeneralDataSource {

    fun requestMoviesImagesCollection(movieTitle: String, callback: LoadDataCallback<PhotoDetailsResponse>)

}