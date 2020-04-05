package com.ilhamfidatama.moviecatalog.helper

interface SearchUpdateListener{
    fun update()
}

object SearchUpdateHelper{
    var searchListener: SearchUpdateListener? = null
    fun addListener(listener: SearchUpdateListener){
        searchListener = listener
    }
    fun update(){
        searchListener?.update()
    }
}

interface SearchHelper{
    fun getSearchQuery(): String?
}

object Searching{
    private var searchQuery: String? = null

    fun getQuery() = searchQuery

    fun addQuery(search: String?){
        run {
            searchQuery = search
            SearchUpdateHelper.update()
        }
    }
}