package com.smsrn.movieshowcase.models

import android.os.Parcel
import android.os.Parcelable
import com.smsrn.movieshowcase.util.Constants.DIGIT_ZERO
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

/**
 * Created by Sibtain Raza on 5/14/2020.
 * smsibtainrn@gmail.com
 */

class MovieDetails() : Parcelable {
    var title: String? = null
    var year: Int? = null
    var cast: ArrayList<String>? = null
    var genres: ArrayList<String>? = null
    var rating: Int? = null

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        title?.let { dest?.writeString(it) }
        year?.let { dest?.writeInt(it) }
        cast?.let { dest?.writeStringList(it) }
        genres?.let { dest?.writeStringList(it) }
        rating?.let { dest?.writeInt(it) }
    }

    override fun describeContents(): Int {
        return DIGIT_ZERO
    }

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        year = parcel.readInt()
        cast = parcel.createStringArrayList()
        genres = parcel.createStringArrayList()
        rating = parcel.readInt()
    }

    companion object CREATOR : Parcelable.Creator<MovieDetails> {
        override fun createFromParcel(parcel: Parcel): MovieDetails {
            return MovieDetails(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetails?> {
            return arrayOfNulls(size)
        }
    }
}