package com.smsrn.movieshowcase.source.remote.api.flickr

import com.smsrn.movieshowcase.models.response.PhotoDetailsResponse
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.API_KEY
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.PAGE
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.TEXT
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
interface ApiHostUrlsFlickr {

    @GET(ApiRoutesFlickr.GET_FLICKR_IMAGES_COLLECTION)
    fun getMovieImageCollectionFlickr(
        @Query(API_KEY) apiKey: String,
        @Query(TEXT) movieTitle: String,
        @Query(PAGE) page: Int
    ): Call<PhotoDetailsResponse>
}