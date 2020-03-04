package com.ilhamfidatama.moviecatalog.offline

interface DatabaseLocal<U> {
    fun addData(id: Int, title: String, posterPath: String, popularity: Double, overview: String)
    fun deleteData(idData: Int)
    fun deleteAllData()
    fun getAllData(): MutableList<U>
    fun getData(id: Int): U?
    fun findData(id: Int): Boolean
}