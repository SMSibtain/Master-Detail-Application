package com.smsrn.movieshowcase.source.remote.api.flickr

import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Url


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
interface ApiHostUrlsFlickr {

    @POST(ApiRoutesFlickr.GET_FLICKR_IMAGES_COLLECTION)
    fun getMovieImageCollectionFlickr(
        @Url asd: String
    ): Call<PhotoDetailsResponse>
}