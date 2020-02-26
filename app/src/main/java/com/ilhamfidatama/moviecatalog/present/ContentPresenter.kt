package com.ilhamfidatama.moviecatalog.present

import android.view.View

class ContentPresenter {
    fun setProgressBar(progressBar: View, show: Boolean){
        if (show){ progressBar.visibility = View.VISIBLE }
        else{ progressBar.visibility = View.GONE }
    }
}