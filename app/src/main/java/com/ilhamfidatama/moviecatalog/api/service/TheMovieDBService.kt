package com.ilhamfidatama.moviecatalog.api.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {
    @GET("3/discover/movie")
    fun getMovie(@Query("api_key") api_key: String, @Query("language") language: String): Call<BaseResponseMovie>

    @GET("3/discover/tv")
    fun getTVShow(@Query("api_key") api_key: String, @Query("language") language: String): Call<BaseResponseTV>
}