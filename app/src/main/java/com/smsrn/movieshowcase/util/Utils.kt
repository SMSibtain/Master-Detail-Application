package com.smsrn.movieshowcase.util

import com.smsrn.movieshowcase.models.response.PhotoDetails


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
object Utils {
    fun getPhotoUrlByDetails(photoDetails: PhotoDetails) {
        "http://farm{${photoDetails.farm}}.static.flickr.com/{${photoDetails.server}}/{${photoDetails.id}}_{${photoDetails.secret}}.jpg"
    }
}