package com.smsrn.movieshowcase.source.remote

import com.smsrn.movieshowcase.source.remote.api.flickr.ApiHostUrlsFlickr
import com.smsrn.movieshowcase.util.NetworkUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Backend {
    companion object {

        val flicker: ApiHostUrlsFlickr by lazy {
            invoke(
                ApiHostUrlsFlickr::class.java,
                "https://api.flickr.com"
            )
        }

        private operator fun <T> invoke(remoteServiceInterface: Class<T>, remoteUrl: String): T {
            return getRetrofitBuilder()
                .baseUrl(remoteUrl)
                .build()
                .create(remoteServiceInterface) as T
        }

        private val client: OkHttpClient = NetworkUtil.enableTls12OnPreLollipop().build()

        private fun getRetrofitBuilder(): Retrofit.Builder {
            return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
        }
    }
}