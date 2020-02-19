package com.ilhamfidatama.moviecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilhamfidatama.moviecatalog.adapter.MenuAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menu = resources.getStringArray(R.array.menu_catalog)
        var menuAdapter = MenuAdapter(this, menu, supportFragmentManager)
        contentApp.adapter = menuAdapter
        tabsMenu.setupWithViewPager(contentApp)

        supportActionBar?.elevation = 0f
    }
}
