package com.ilhamfidatama.moviecatalog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamfidatama.moviecatalog.DescriptionFavTVActivity
import com.ilhamfidatama.moviecatalog.DescriptionTVShowActivity
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteTV
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import kotlinx.android.synthetic.main.layout_list_tv.view.*

class FavoriteTVAdapter(val context: Context): RecyclerView.Adapter<FavoriteTVAdapter.ViewHolder>() {

    private var favTVList = mutableListOf<FavoriteTV>()

    fun addData(list: MutableList<FavoriteTV>){
        favTVList.clear()
        favTVList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_tv, parent, false))

    override fun getItemCount(): Int = favTVList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favTVList[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(favTV: FavoriteTV){
            val imageBitmap = FavoritePresenter().loadImage(favTV.posterPath)
            itemView.let {
                it.tittle_tv.text = favTV.title
                it.rating_tv.text = favTV.popularity.toString()
                it.image_tv.setImageBitmap(imageBitmap)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, DescriptionFavTVActivity::class.java)
                intent.putExtra(DescriptionFavTVActivity.EXTRA_FAV_TV, favTV)
                context.startActivity(intent)
            }
        }

    }


}