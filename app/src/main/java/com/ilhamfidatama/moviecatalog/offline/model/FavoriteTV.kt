package com.ilhamfidatama.moviecatalog.offline.model

import android.os.Parcelable
import com.orm.SugarRecord
import kotlinx.android.parcel.Parcelize

@Parcelize
class FavoriteTV(var idTV: Int,
                 var title: String,
                 var posterPath: String,
                 var popularity: Double,
                 var overview: String) : SugarRecord(), Parcelable {

    constructor() : this(0, "", "", 0.0, "")

}