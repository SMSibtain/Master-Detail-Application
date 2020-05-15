package com.smsrn.movieshowcase.util

import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.smsrn.movieshowcase.MasterDetailApp
import com.smsrn.movieshowcase.models.response.PhotoDetails
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.apache.commons.lang3.ObjectUtils
import org.apache.commons.lang3.StringUtils
import java.io.IOException


/**
 * Created by Sibtain Raza on 5/15/2020.
 * smsibtainrn@gmail.com
 */
object Utils {

    /**
     * Get photo url by details
     * @param photoDetails : Photo Details Object
     */
    fun getPhotoUrlByDetails(photoDetails: PhotoDetails): String? {
        var imageUrl: String? = null
        NullAwayUtils.safeLet(
            photoDetails.farm,
            photoDetails.server,
            photoDetails.id,
            photoDetails.secret
        ) { farm, server, id, secret ->
            imageUrl = "http://farm{$farm}.static.flickr.com/{$server}/{$id}_{$secret}.jpg"
        }
        return imageUrl
    }

    /**
     * Get json data from asset
     * @param fileName : File Name
     */
    fun getJsonDataFromAsset(fileName: String): String {
        var jsonString: String = StringUtils.EMPTY
        try {
            jsonString = MasterDetailApp.context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return jsonString
    }

    /**
     * Load Image Picasso
     * @param appCompatImageView : App Compat Image View
     * @param placeHolder : Placeholder if image does not load
     * @param link : Link for image
     */
    fun loadImgPicasso(appCompatImageView: AppCompatImageView? = null, placeHolder: Int, link: String) {
        if (appCompatImageView != null && StringUtils.isNotEmpty(link)) {
            Picasso.get().load(link)
                .fit().centerInside()
                .placeholder(placeHolder)
                .into(appCompatImageView, object : Callback {
                    override fun onSuccess() {
                        Log.v("Picasso", "onSuccess")
                    }

                    override fun onError(e: Exception) {
                        Log.v("Picasso", "onError")
                    }
                })
        }
    }
}