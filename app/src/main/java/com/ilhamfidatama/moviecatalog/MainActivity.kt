package com.ilhamfidatama.moviecatalog

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ilhamfidatama.moviecatalog.adapter.MenuAdapter
import com.ilhamfidatama.moviecatalog.helper.Searching
import com.orm.SugarContext
import com.orm.SugarDb
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.movie -> {
                contentApp.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.tv -> {
                contentApp.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.fav -> {
                contentApp.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SugarContext.init(applicationContext)
        val databaseLocal = SugarDb(this)
        databaseLocal.onCreate(databaseLocal.db)

        val menu = resources.getStringArray(R.array.home_menu)
        val menuAdapter = MenuAdapter(menu, supportFragmentManager)
        contentApp.adapter = menuAdapter

        val bottomNav = findViewById<BottomNavigationView>(R.id.tabsMenu)
        bottomNav.setOnNavigationItemSelectedListener(navigationListener)

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val btnSearch = menu?.findItem(R.id.search)?.actionView as SearchView
        btnSearch.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        btnSearch.queryHint = resources.getString(R.string.search)
        btnSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Searching.addQuery(query)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.language){
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
