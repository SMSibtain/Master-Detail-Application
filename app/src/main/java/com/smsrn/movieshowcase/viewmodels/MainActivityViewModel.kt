package com.smsrn.movieshowcase.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smsrn.movieshowcase.models.MovieDetails
import com.smsrn.movieshowcase.models.response.MovieDetailsResponse
import com.smsrn.movieshowcase.util.Constants
import com.smsrn.movieshowcase.util.Constants.DIGIT_THREE
import com.smsrn.movieshowcase.util.Utils
import okhttp3.internal.Util
import org.apache.commons.lang3.StringUtils

/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
class MainActivityViewModel : ViewModel() {

    private var _items: ArrayList<MovieDetails> = ArrayList()
    val items: ArrayList<MovieDetails>
        get() = _items

    private val _itemsFiltered = MutableLiveData<ArrayList<MovieDetails>>().apply { value = ArrayList() }
    val itemsFiltered: MutableLiveData<ArrayList<MovieDetails>>
        get() = _itemsFiltered

    var SEARCH_VIEW_FILTER_VALUE = StringUtils.EMPTY
    var SEARCH_SORTING_ORDER = DIGIT_THREE

    /**
     * Get movies list from json file in assets
     */
    fun getMoviesList() {
        val moviesJsonString: String = Utils.getJsonDataFromAsset(Constants.AssetsNames.MOVIES_JSON_FILE_NAME)
        if (moviesJsonString.isNotEmpty()) {
            val movieDetailsResponse: MovieDetailsResponse = Utils.getGson().fromJson(
                moviesJsonString,
                object : TypeToken<MovieDetailsResponse>() {}.type
            )
            movieDetailsResponse.movies?.let { _items = it }
            setOrResetList()
        }
    }

    fun setOrResetList() {
        _itemsFiltered.value = _items
    }
}
