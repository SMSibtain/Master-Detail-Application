package com.smsrn.movieshowcase.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.smsrn.movieshowcase.factory.ViewModelFactory


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */

/**
 * Activity Extension functions
 */
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(viewModelClass)

/**
 * Navigate to target activity
 * @param targetActivityClass : Activity to navigate
 */
fun <T : AppCompatActivity> AppCompatActivity.gotoActivity(targetActivityClass: Class<T>) {
    val intent = Intent(this, targetActivityClass)
    startActivity(intent)
}

/**
 * Navigate to target activity with intent data
 * @param targetActivityClass : Activity to navigate
 * @param intentKey : Key
 * @param intentValue : Value
 */
fun AppCompatActivity.gotoActivity(
    targetActivityClass: Class<*>,
    intentKey: String,
    intentValue: Any? = null
) {
    val i = Intent(this, targetActivityClass)
    i.putExtra(intentKey, intentValue)
    startActivity(i)
}