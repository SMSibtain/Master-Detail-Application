package com.smsrn.movieshowcase.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smsrn.movieshowcase.R
import com.smsrn.movieshowcase.adapter.LastAdapter
import com.smsrn.movieshowcase.util.Constants.DIGIT_TWO
import com.smsrn.movieshowcase.util.Constants.DIGIT_ZERO
import org.apache.commons.lang3.StringUtils


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */

object BindingAdapters {

    /**
     * Use To Set Recycler View
     */
    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, list: List<Any>? = null) {
        with(recyclerView.adapter as LastAdapter<Any>) {
            if (list != null)
                items = ArrayList(list)
        }
    }

    @BindingAdapter("app:stringValues")
    @JvmStatic
    fun setStringValues(appCompatTextView: AppCompatTextView, stringArrayList: ArrayList<String>? = null) {
        var stringValues = StringUtils.EMPTY
        stringArrayList?.forEach {
            stringValues = "$stringValues$it, ${StringUtils.EMPTY}"
        }
        if (stringValues.isNotEmpty()) {
            stringValues = stringValues.substring(DIGIT_ZERO, stringValues.length - DIGIT_TWO)
            appCompatTextView.text = stringValues
        }
    }

    /**
     * This will load image from url directly from xml
     */
    @BindingAdapter("app:loadImageUrl")
    @JvmStatic
    fun setLoadImageUrl(appCompatImageView: AppCompatImageView, url: String? = null) {
        url?.let {
            Utils.loadImgPicasso(appCompatImageView, R.color.white, it)
        }
    }
}