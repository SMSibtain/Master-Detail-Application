package com.smsrn.movieshowcase.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */

fun <T : AppCompatActivity> AppCompatActivity.gotoActivity(targetActivityClass: Class<T>) {
    val intent = Intent(this, targetActivityClass)
    startActivity(intent)
}


fun AppCompatActivity.gotoActivity(
    targetActivityClass: Class<*>,
    intentKey: String,
    intentValue: Any? = null
) {
    val i = Intent(this, targetActivityClass)
    i.putExtra(intentKey, intentValue)
    startActivity(i)
}