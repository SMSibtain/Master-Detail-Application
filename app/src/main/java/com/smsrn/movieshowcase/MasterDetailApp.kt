package com.smsrn.movieshowcase

import android.app.Application
import android.content.Context

/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
class MasterDetailApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (application == null) {
            application = this
        }
    }

    companion object {
        var application: MasterDetailApp? = null
            private set

        val context: Context
            get() = application!!.applicationContext
    }
}
