package com.smsrn.movieshowcase.source.remote.api.flickr


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
object ApiRoutesFlickr {

    /**
     * Get images collection from flickr
     */
    const val GET_FLICKR_IMAGES_COLLECTION =
        "/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&per_page=10"
}