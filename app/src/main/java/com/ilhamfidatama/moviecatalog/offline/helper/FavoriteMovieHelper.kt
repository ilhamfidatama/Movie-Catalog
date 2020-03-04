package com.ilhamfidatama.moviecatalog.offline.helper

import com.ilhamfidatama.moviecatalog.offline.DatabaseLocal
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteMovie
import com.orm.SugarRecord

object FavoriteMovieHelper:
    DatabaseLocal<FavoriteMovie> {
    override fun addData(
        id: Int,
        title: String,
        posterPath: String,
        popularity: Double,
        overview: String
    ) {
        val movie = FavoriteMovie(id, title, posterPath, popularity, overview)
        movie.save()
    }

    override fun deleteData(idData: Int) {
        val movie = getData(idData)
        movie?.delete()
    }

    override fun deleteAllData() {
        SugarRecord.deleteAll(FavoriteMovie::class.java)
    }

    override fun getAllData(): MutableList<FavoriteMovie> = SugarRecord.listAll(FavoriteMovie::class.java)

    override fun getData(id: Int): FavoriteMovie? = getAllData().find { it.idMovie == id }

    override fun findData(id: Int): Boolean{
        val data = getData(id)
        return data != null
    }

}