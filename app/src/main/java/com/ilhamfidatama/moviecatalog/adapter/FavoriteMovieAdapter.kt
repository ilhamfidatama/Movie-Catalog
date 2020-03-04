package com.ilhamfidatama.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamfidatama.moviecatalog.DescriptionFavMovieActivity
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteMovie
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import kotlinx.android.synthetic.main.layout_list_movie.view.*

class FavoriteMovieAdapter(val context: Context): RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    private var favMovieList = mutableListOf<FavoriteMovie>()

    fun addData(list: MutableList<FavoriteMovie>){
        favMovieList.clear()
        favMovieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_list_movie, parent, false))

    override fun getItemCount(): Int = favMovieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favMovieList[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(favoriteMovie: FavoriteMovie){
            val imageBitmap = FavoritePresenter().loadImage(favoriteMovie.posterPath)
            itemView.let {
                it.movie_title.text = favoriteMovie.title
                it.rating_movie.text = favoriteMovie.popularity.toString()
                it.film_image.setImageBitmap(imageBitmap)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, DescriptionFavMovieActivity::class.java)
                intent.putExtra(DescriptionFavMovieActivity.EXTRA_FAV_MOVIE, favoriteMovie)
                Log.e("intent_favMovie", "${intent.extras?.getParcelable<FavoriteMovie>("extra_fav_movie")?.title}")
                context.startActivity(intent)
            }
        }
    }
}