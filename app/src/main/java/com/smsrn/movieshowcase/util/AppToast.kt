package com.smsrn.movieshowcase.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.StringRes
import com.smsrn.movieshowcase.BuildConfig
import com.smsrn.movieshowcase.MasterDetailApp

/**
 * Created by Sibtain Raza on 4/6/2020.
 * smsibtainrn@gmail.com
 */

object AppToast {
    private var mToast: Toast? = null

    fun showToast(toastMessage: String) {
        createToast(toastMessage, Toast.LENGTH_SHORT)
    }

    fun showToast(@StringRes resId: Int) {
        createToast(MasterDetailApp.context.getString(resId), Toast.LENGTH_SHORT)
    }

    fun showDebugToast(toastMessage: String) {
        if (BuildConfig.DEBUG)
            createToast(toastMessage, Toast.LENGTH_SHORT)
    }

    fun showLongToast(toastMessage: String) {
        createToast(toastMessage, Toast.LENGTH_LONG)
    }

    fun showLongToast(@StringRes resId: Int) {
        createToast(MasterDetailApp.context.getString(resId), Toast.LENGTH_LONG)
    }

    private fun createToast(string: String?, toastDuration: Int) {
        mToast?.cancel()
        mToast = Toast.makeText(MasterDetailApp.context, string, toastDuration)
        Handler(Looper.getMainLooper()).post {
            try {
                mToast?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}