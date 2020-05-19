package com.smsrn.movieshowcase.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.smsrn.movieshowcase.util.DialogUtils


/**
 * Created by Sibtain Raza on 5/16/2020.
 * smsibtainrn@gmail.com
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    fun showProgressDialog() {
        DialogUtils.showProgressDialog(this)
    }
}