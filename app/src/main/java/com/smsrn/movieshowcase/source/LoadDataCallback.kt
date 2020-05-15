package com.smsrn.movieshowcase.source


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
interface LoadDataCallback<T> {
    fun onDataLoaded(response: T) {
    }

    fun onDataNotAvailable(errorCode: Int, reasonMsg: String) {

    }
}