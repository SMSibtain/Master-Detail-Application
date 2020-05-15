package com.smsrn.movieshowcase.extensions

import android.content.Intent
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Sibtain Raza on 5/6/2020.
 * smsibtainrn@gmail.com
 */

fun Intent.putExtra(intentKey: String, intentValue: Any?) {
    when (intentValue) {
        is Boolean -> putExtra(intentKey, intentValue)
        is Int -> putExtra(intentKey, intentValue)
        is Float -> putExtra(intentKey, intentValue)
        is Double -> putExtra(intentKey, intentValue)
        is Long -> putExtra(intentKey, intentValue)
        is String -> putExtra(intentKey, intentValue)
        is Serializable -> putExtra(intentKey, intentValue)
        is Parcelable -> putExtra(intentKey, intentValue)
    }
}