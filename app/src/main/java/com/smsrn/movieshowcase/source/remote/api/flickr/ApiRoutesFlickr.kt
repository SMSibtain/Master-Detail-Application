package com.smsrn.movieshowcase.source.remote.api.flickr

import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.API_KEY
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.FARM
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.ID
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.MOVIE_TITLE
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.SECRET
import com.smsrn.movieshowcase.source.remote.api.flickr.ApiFieldsFlickr.SERVER


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
object ApiRoutesFlickr {

    /**
     * Get images collection from flickr
     */
    const val GET_FLICKR_IMAGES_COLLECTION =
        "/services/rest/?method=flickr.photos.search&api_key={$API_KEY}&format=json&nojsoncallback=1&text={$MOVIE_TITLE}&page=1&per_page=10"
}