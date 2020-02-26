package com.ilhamfidatama.moviecatalog.api

import com.ilhamfidatama.moviecatalog.api.service.TheMovieDBService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Api {

    fun create(): TheMovieDBService{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/")
            .build()
        return retrofit.create(TheMovieDBService::class.java)
    }
}