package com.smsrn.movieshowcase.models

import java.util.ArrayList

/**
 * Created by Sibtain Raza on 5/14/2020.
 * smsibtainrn@gmail.com
 */

data class MovieDetails(
    val title: String?,
    val year: Int?,
    val cast: ArrayList<String>?,
    val genres: ArrayList<String>?,
    val rating: Int
)