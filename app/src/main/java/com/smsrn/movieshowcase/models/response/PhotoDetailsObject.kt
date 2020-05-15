package com.smsrn.movieshowcase.models.response


/**
 * Created by Sibtain Raza on 5/16/2020.
 * smsibtainrn@gmail.com
 */
data class PhotoDetailsObject(
    var page: Int,
    var pages: Int,
    var perpage: Int,
    var total: String,
    var photo: ArrayList<PhotoDetails>
)