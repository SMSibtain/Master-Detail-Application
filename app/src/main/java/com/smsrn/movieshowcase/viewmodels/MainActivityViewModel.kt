package com.smsrn.movieshowcase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.models.response.MovieDetailsResponse
import com.smsrn.movieshowcase.util.Constants
import com.smsrn.movieshowcase.util.Utils

/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
class MainActivityViewModel : ViewModel() {

    private val _items = MutableLiveData<ArrayList<MovieDetails>>().apply { value = ArrayList() }
    val items: MutableLiveData<ArrayList<MovieDetails>>
        get() = _items

    fun getMoviesList() {
        val moviesJsonString: String = Utils.getJsonDataFromAsset(Constants.AssetsNames.MOVIES_JSON_FILE_NAME)
        if (moviesJsonString.isNotEmpty()) {
            val movieDetailsResponse: MovieDetailsResponse = Gson().fromJson(
                moviesJsonString,
                object : TypeToken<MovieDetailsResponse>() {}.type
            )
            _items.value = movieDetailsResponse.movies
        }
    }
}
