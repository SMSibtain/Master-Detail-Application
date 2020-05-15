package com.smsrn.movieshowcase.util


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
object Constants {

    const val DIGIT_ZERO : Int = 0
    const val DIGIT_ONE : Int = 1
    const val DIGIT_TWO : Int = 2

    const val FLICKR_API_KEY = "441715ce30e3de7ff443ce0d86e946fb"

    const val ERROR_PLEASE_TRY_AGAIN = "Something went wrong. Please try again later."
    const val CONNECTION_ERROR_MSG =
        "Problem in internet connectivity. Check your internet connection."

    object AssetsNames{
        const val MOVIES_JSON_FILE_NAME = "movies.json"
    }

    object IntentKeys {
        const val MOVIE_DETAIL_OBJECT = "MOVIE_DETAIL_OBJECT"
    }

    /**
     * Inner class for API Status Codes Constants
     */
    internal object ApiStatusCode {
        const val REQUEST_RESULT_OK = "OK"
        const val OK = 200
        const val CHECK_SUB_CODE = 422
        const val UNAUTHORIZED = 401
        const val INTERNAL_SERVER_ERROR = 500
    }
}