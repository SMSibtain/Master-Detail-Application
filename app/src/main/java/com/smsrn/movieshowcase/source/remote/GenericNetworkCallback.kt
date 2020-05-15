package com.smsrn.movieshowcase.source.remote

import com.smsrn.movieshowcase.source.LoadDataCallback
import com.smsrn.movieshowcase.util.Constants.ApiStatusCode.INTERNAL_SERVER_ERROR
import com.smsrn.movieshowcase.util.Constants.CONNECTION_ERROR_MSG
import com.smsrn.movieshowcase.util.Constants.ERROR_PLEASE_TRY_AGAIN
import com.smsrn.movieshowcase.util.DialogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


/**
 * Generic Retrofit Call Back to receive Network Call response and send that to previous Layer
 */
class GenericNetworkCallback<T>(var callback: LoadDataCallback<T>) :
    Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.body() == null) {
            if (response.errorBody() != null) {
                try {
                    onRequestFail(response.code(), response.message(), callback)
                } catch (e: Exception) {
                    e.printStackTrace()
                    callback.onDataNotAvailable(INTERNAL_SERVER_ERROR, ERROR_PLEASE_TRY_AGAIN)
                }
            } else {
                callback.onDataNotAvailable(INTERNAL_SERVER_ERROR, ERROR_PLEASE_TRY_AGAIN)
            }
            return
        }

        if (response.isSuccessful && response.code() in 200..300) {
            callback.onDataLoaded(response.body()!!)
        } else {
            onRequestFail(response.code(), response.message(), callback)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        DialogUtils.hideProgressDialog()
        t.printStackTrace()
        if (!call.isCanceled) {
            callback.onDataNotAvailable(INTERNAL_SERVER_ERROR, getErrorMessage(t))
        }
    }


    /**
     * parse error message coming from response
     * @param error Throwable
     */
    private fun getErrorMessage(error: Throwable): String {
        DialogUtils.hideProgressDialog()
        return when (error) {
            is IOException -> CONNECTION_ERROR_MSG
            else -> ERROR_PLEASE_TRY_AGAIN
        }
    }

    /**
     * Handles Error cases when get proper response from API with Error code. Clears session if user
     * is UnAuthorized or gives callback to Repository layer.
     *
     * @param errorCode Request Error Code
     * @param subCode Request Sub Error Code
     * @param errorMsg Error Message
     * @param callback Callback handler
     */
    private fun onRequestFail(errorCode: Int, errorMsg: String, callback: LoadDataCallback<T>) {
        callback.onDataNotAvailable(errorCode, errorMsg)
    }
}