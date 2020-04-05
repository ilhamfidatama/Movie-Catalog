package com.ilhamfidatama.moviecatalog.present

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.ilhamfidatama.moviecatalog.api.Api
import com.ilhamfidatama.moviecatalog.api.service.BaseResponseMovie
import com.ilhamfidatama.moviecatalog.api.service.BaseResponseTV
import com.ilhamfidatama.moviecatalog.model.Movie
import com.ilhamfidatama.moviecatalog.model.TVShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelPresenter: ViewModel() {
    private val API_KEY = "a67d7e32992cae36c71dcc2c701e1c00"
    private val API_SERVICE = Api.create()
    private var listMovie = MutableLiveData<ArrayList<Movie>>()
    private var listTVSShow = MutableLiveData<ArrayList<TVShow>>()

    fun getMovie(language: String){
        API_SERVICE.getMovie(API_KEY, language).enqueue(object : Callback<BaseResponseMovie>{
            override fun onFailure(call: Call<BaseResponseMovie>, t: Throwable) {
                Log.e("load-movie", "${t.message}")
            }

            override fun onResponse(
                call: Call<BaseResponseMovie>,
                response: Response<BaseResponseMovie>
            ) {
                proccessDataMovie(response.body())
            }
        })
    }

    fun getTVShow(language: String){
        API_SERVICE.getTVShow(API_KEY, language).enqueue(object : Callback<BaseResponseTV>{
            override fun onFailure(call: Call<BaseResponseTV>, t: Throwable) {
                Log.e("load-tvShow", "${t.message}")
            }

            override fun onResponse(
                call: Call<BaseResponseTV>,
                response: Response<BaseResponseTV>
            ) {
                processDataTVShow(response.body())
            }
        })
    }

    fun searchMovie(query: String, language: String){
        API_SERVICE.searchMovie(API_KEY, query, language).enqueue(object : Callback<BaseResponseMovie>{
            override fun onFailure(call: Call<BaseResponseMovie>, t: Throwable) {
                Log.e("search-movie", "${t.message}")
            }

            override fun onResponse(
                call: Call<BaseResponseMovie>,
                response: Response<BaseResponseMovie>
            ) {
                proccessDataMovie(response.body())
            }

        })
    }

    fun searchTV(query: String, language: String){
        API_SERVICE.searchTV(API_KEY, query, language).enqueue(object : Callback<BaseResponseTV>{
            override fun onFailure(call: Call<BaseResponseTV>, t: Throwable) {
                Log.e("search-tv", "${t.message}")
            }

            override fun onResponse(
                call: Call<BaseResponseTV>,
                response: Response<BaseResponseTV>
            ) {
                processDataTVShow(response.body())
            }

        })
    }

    fun proccessDataMovie(data: BaseResponseMovie?){
        data?.results.let {
            listMovie.postValue(it)
        }
    }

    fun processDataTVShow(data: BaseResponseTV?){
        data?.results.let {
            listTVSShow.postValue(it)
        }
    }

    @SuppressLint("CheckResult")
    fun loadImage(context: Context, poster_path: String?): RequestBuilder<Drawable>{
        return Glide.with(context)
            .load("https://image.tmdb.org/t/p/w185$poster_path")
            .apply(RequestOptions().override(130,200))
    }

    fun getListMovie(): LiveData<ArrayList<Movie>> = listMovie

    fun getListTV(): LiveData<ArrayList<TVShow>> = listTVSShow
}