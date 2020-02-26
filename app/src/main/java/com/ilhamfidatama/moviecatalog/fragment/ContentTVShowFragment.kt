package com.ilhamfidatama.moviecatalog.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.adapter.TVShowAdapter
import com.ilhamfidatama.moviecatalog.present.ContentPresenter
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.fragment_content_catalog.*

class ContentTVShowFragment: Fragment() {
    private lateinit var contextActivity: Context
    private lateinit var adapter: TVShowAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            contextActivity = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.w("bug", "tv show fragment")
        setProgressBar(true)
        adapter = TVShowAdapter(contextActivity)
        adapter.notifyDataSetChanged()
        listContent.layoutManager = LinearLayoutManager(contextActivity)
        listContent.adapter = adapter
        getTVShow()
    }

    fun getTVShow(){
        val tvModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ModelPresenter::class.java)
        tvModel.getTVShow(getString(R.string.language_api))
        tvModel.getListTV().observe(this, Observer {
            it?.let {
                adapter.addData(it)
                setProgressBar(false)
            }
        })
    }

    private fun setProgressBar(show: Boolean){
        ContentPresenter().setProgressBar(progressBar, show)
    }
}