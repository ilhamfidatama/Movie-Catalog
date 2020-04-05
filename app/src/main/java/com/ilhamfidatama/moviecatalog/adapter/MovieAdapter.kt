package com.ilhamfidatama.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamfidatama.moviecatalog.DescriptionMovieActivity
import com.ilhamfidatama.moviecatalog.model.Movie
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.layout_list_movie.view.*
import kotlinx.android.synthetic.main.layout_list_movie.view.movie_title

class MovieAdapter(val context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = arrayListOf<Movie>()

    fun addData(listMovie: ArrayList<Movie>?){
        movies.clear()
        listMovie?.let { movies.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_movie, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(movie: Movie){
            itemView.movie_title.text = movie.title
            itemView.rating_movie.text = movie.popularity.toString()
            val glide = ModelPresenter().loadImage(context, movie.poster_path)
            glide.into(itemView.film_image)

            itemView.setOnClickListener {
                val intent = Intent(context, DescriptionMovieActivity::class.java)
                intent.putExtra(DescriptionMovieActivity.EXTRA_MOVIE, movie)
                context.startActivity(intent)
            }
        }
    }
}