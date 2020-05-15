package com.smsrn.movieshowcase.util

import com.smsrn.movieshowcase.source.GeneralRepository
import com.smsrn.movieshowcase.source.local.GeneralLocalDataSource
import com.smsrn.movieshowcase.source.remote.GeneralRemoteDataSource


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
object Injection {

    fun provideGeneralRepository(): GeneralRepository {
        return GeneralRepository(GeneralLocalDataSource(), GeneralRemoteDataSource())
    }

}