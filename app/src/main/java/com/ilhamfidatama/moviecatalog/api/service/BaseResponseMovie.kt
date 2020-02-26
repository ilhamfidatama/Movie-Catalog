package com.ilhamfidatama.moviecatalog.api.service

import com.ilhamfidatama.moviecatalog.model.Movie

data class BaseResponseMovie(
    var results: ArrayList<Movie>
)