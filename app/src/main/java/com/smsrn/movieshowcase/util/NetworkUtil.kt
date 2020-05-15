package com.smsrn.movieshowcase.util

import android.os.Build
import android.util.Log
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.TlsVersion
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext

/**
 * Created by Sibtain Raza on 4/7/2020.
 * smsibtainrn@gmail.com
 */

object NetworkUtil {

    /**
     * Enable TLS v1.2 Support For Pre Lolliop Version
     */
    fun enableTls12OnPreLollipop(): OkHttpClient.Builder {
        /*val loggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }*/
        val client: OkHttpClient.Builder = OkHttpClient.Builder().apply {
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
            addNetworkInterceptor {
                val requestBuilder: Request.Builder = it.request().newBuilder();
                requestBuilder.header("Content-Type", "application/json");
                it.proceed(requestBuilder.build())
            }
            /*if (BuildConfig.DEBUG) addNetworkInterceptor(LoggingInterceptor())*/
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            try {
                val sc = SSLContext.getInstance("TLSv1.2")
                sc.init(null, null, null)
                client.sslSocketFactory(Tls12SocketFactory(sc.socketFactory))

                val cs = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .build()

                val specs = ArrayList<ConnectionSpec>().apply {
                    add(cs)
                    add(ConnectionSpec.COMPATIBLE_TLS)
                    add(ConnectionSpec.CLEARTEXT)
                }

                client.connectionSpecs(specs)
            } catch (exc: Exception) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc)
            }
        }
        return client
    }
}