package com.ilhamfidatama.moviecatalog.offline.helper

import com.ilhamfidatama.moviecatalog.offline.DatabaseLocal
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteTV
import com.orm.SugarRecord

object FavoriteTVHelper: DatabaseLocal<FavoriteTV> {
    override fun addData(
        id: Int,
        title: String,
        posterPath: String,
        popularity: Double,
        overview: String
    ) {
        val tv = FavoriteTV(id, title, posterPath, popularity, overview)
        tv.save()
    }

    override fun deleteData(idData: Int) {
        val tv = getData(idData)
        tv?.delete()
    }

    override fun deleteAllData() {
        SugarRecord.deleteAll(FavoriteTV::class.java)
    }

    override fun getAllData(): MutableList<FavoriteTV> = SugarRecord.listAll(FavoriteTV::class.java)

    override fun getData(id: Int): FavoriteTV? = getAllData().find { it.idTV == id }

    override fun findData(id: Int): Boolean {
        val tv = getData(id)
        return tv != null
    }
}