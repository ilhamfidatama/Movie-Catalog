package com.ilhamfidatama.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamfidatama.moviecatalog.DescriptionTVShowActivity
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.model.TVShow
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.layout_list_tv.view.*

class TVShowAdapter(val context: Context): RecyclerView.Adapter<TVShowAdapter.ViewHolder>() {

    private var listTVShow = arrayListOf<TVShow>()

    fun addData(data: ArrayList<TVShow>){
        listTVShow.clear()
        listTVShow.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_list_tv, parent, false))

    override fun getItemCount(): Int = listTVShow.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTVShow[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(tvShow: TVShow){
            itemView.tittle_tv.text = tvShow.name
            itemView.rating_tv.text = tvShow.popularity.toString()
            val glide = ModelPresenter().loadImage(context, tvShow.poster_path)
            glide.into(itemView.image_tv)

            itemView.setOnClickListener {
                Log.w("tv-click", "$tvShow")
                val intent = Intent(context, DescriptionTVShowActivity::class.java)
                intent.putExtra(DescriptionTVShowActivity.EXTRA_TV_SHOW, tvShow)
                Log.w("tv-click", "${intent.extras}")
                context.startActivity(intent)
            }
        }
    }
}