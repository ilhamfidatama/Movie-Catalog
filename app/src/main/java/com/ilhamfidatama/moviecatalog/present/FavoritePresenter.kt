package com.ilhamfidatama.moviecatalog.present

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteMovieHelper
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteTVHelper
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteMovie
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteTV
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class FavoritePresenter: ViewModel() {
    private var favoriteMovieList = MutableLiveData<MutableList<FavoriteMovie>>()
    private var favoriteTVList = MutableLiveData<MutableList<FavoriteTV>>()

    fun loadFavMovie(){
        FavoriteMovieHelper.getAllData().let {
            favoriteMovieList.postValue(it)
        }
    }

    fun getFavMovie() = favoriteMovieList

    fun loadFavTV(){
        FavoriteTVHelper.getAllData().let {
            favoriteTVList.postValue(it)
        }
    }

    fun getFavTV() = favoriteTVList

    fun loadImage(filePath: String): Bitmap{
        val imageFile = File(filePath)
        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
        return bitmap
    }

    fun saveImageToStorage(imageBitmap: Bitmap, storage: File?): String{
        val timestamp = SimpleDateFormat("yyyyMMdd-HHmmss").format(Date())
        val file = File.createTempFile("mc_$timestamp", ".jpeg", storage)
        val save = FileOutputStream(file)
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, save)
        return file.absolutePath
    }
}