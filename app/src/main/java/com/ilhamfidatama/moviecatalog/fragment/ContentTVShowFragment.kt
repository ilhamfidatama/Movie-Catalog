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
import com.ilhamfidatama.moviecatalog.helper.SearchHelper
import com.ilhamfidatama.moviecatalog.helper.SearchUpdateHelper
import com.ilhamfidatama.moviecatalog.helper.SearchUpdateListener
import com.ilhamfidatama.moviecatalog.present.ContentPresenter
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.fragment_content_catalog.*

class ContentTVShowFragment: Fragment(), SearchUpdateListener {

    private lateinit var contextActivity: Context
    private lateinit var adapter: TVShowAdapter
    var searchHelper: SearchHelper? = null

    override fun update() {
        searchHelper?.let {
            getTVShow(it.getSearchQuery())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextActivity = context
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
        adapter = TVShowAdapter(contextActivity)
        adapter.notifyDataSetChanged()
        listContent.layoutManager = LinearLayoutManager(context)
        listContent.adapter = adapter
        getTVShow(null)
    }

    private fun getTVShow(search: String?){
        adapter.addData(null)
        setProgressBar(true)
        val tvModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ModelPresenter::class.java)
        if (search.isNullOrEmpty()){
            tvModel.getTVShow(getString(R.string.language_api))
            tvModel.getListTV().observe(this, Observer {
                it?.let {
                    adapter.addData(it)
                    setProgressBar(false)
                }
            })
        }else{
            tvModel.searchTV(search, getString(R.string.language_api))
            tvModel.getListTV().observe(this, Observer {
                it?.let {
                    adapter.addData(it)
                    setProgressBar(false)
                }
            })
        }
    }

    private fun setProgressBar(show: Boolean){
        ContentPresenter().setProgressBar(progressBar, show)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser){
            Log.e("visible", "tv")
            SearchUpdateHelper.addListener(this)
        }
        super.setUserVisibleHint(isVisibleToUser)
    }
}