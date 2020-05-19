package com.smsrn.movieshowcase.adapter

import com.smsrn.movieshowcase.models.MovieDetails

/**
 * Created by Sibtain Raza on 4/6/2020.
 * smsibtainrn@gmail.com
 */
object LastAdapterFilterUtil {
    fun <T> filterResult(item: T, charString: String): Boolean {
        when (item) {
            is MovieDetails -> {
                item.title?.let {
                    return it.contains(charString, ignoreCase = true)
                }
                return false
            }
        }
        return true
    }
}