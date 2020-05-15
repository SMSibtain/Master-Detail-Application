package com.smsrn.movieshowcase.models.response

import com.smsrn.movieshowcase.models.MovieDetails
import java.util.ArrayList

/**
 * Created by Sibtain Raza on 5/14/2020.
 * smsibtainrn@gmail.com
 */

data class MovieDetailsResponse(
    val movies: ArrayList<MovieDetails>? = null
)