package com.smsrn.movieshowcase.models.response

import java.util.ArrayList

/**
 * Created by Sibtain Raza on 5/14/2020.
 * smsibtainrn@gmail.com
 */
data class PhotoDetailsResponse(
    var photo: ArrayList<PhotoDetails>,
    var page: Int,
    var pages: Int,
    var perpage: Int,
    var total: String
)
