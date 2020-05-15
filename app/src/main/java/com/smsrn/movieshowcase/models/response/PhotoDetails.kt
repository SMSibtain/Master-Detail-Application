package com.smsrn.movieshowcase.models.response

/**
 * Created by Sibtain Raza on 5/14/2020.
 * smsibtainrn@gmail.com
 */
data class PhotoDetails(
    var id: String?,
    var owner: String?,
    var secret: String?,
    var server: String?,
    var farm: Int,
    var title: String?,
    var ispublic: Boolean,
    var isfriend: Boolean,
    var isfamily: Boolean
)