package com.ilhamfidatama.moviecatalog.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow (
    var poster_path: String,
    var popularity: Double,
    var overview: String,
    var name: String
): Parcelable