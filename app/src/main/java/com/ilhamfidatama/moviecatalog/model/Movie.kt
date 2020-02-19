package com.ilhamfidatama.moviecatalog.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var image_movie: Int,
    var movie_title: String,
    var rating_movie: String,
    var desc_movie: String
): Parcelable