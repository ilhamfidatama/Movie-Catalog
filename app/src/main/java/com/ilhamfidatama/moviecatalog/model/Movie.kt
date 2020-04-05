package com.ilhamfidatama.moviecatalog.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var id: Int,
    var poster_path: String?,
    var overview: String,
    var title: String,
    var popularity: Double
): Parcelable