package com.ilhamfidatama.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamfidatama.moviecatalog.DescriptionContentActivity
import com.ilhamfidatama.moviecatalog.model.Movie
import com.ilhamfidatama.moviecatalog.R
import kotlinx.android.synthetic.main.layout_list_view.view.*

class MovieAdapter(val context: Context, private val movies: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_view, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(movie: Movie){
            itemView.movie_title.text = movie.movie_title
            itemView.rating_movie.text = movie.rating_movie
            itemView.film_image.setImageResource(movie.image_movie)

            itemView.setOnClickListener {
                val intent = Intent(context, DescriptionContentActivity::class.java)
                intent.putExtra(DescriptionContentActivity.EXTRA_MOVIE, movie)
                context.startActivity(intent)
            }
        }
    }
}