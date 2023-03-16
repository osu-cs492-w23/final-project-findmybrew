package com.example.findmybrew.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findmybrew.R
import com.example.findmybrew.data.SingleBrewery

const val EXTRA_SINGLE_BREWERY = "com.example.android.findmybrew.SINGLE_BREWERY"

class BreweryDetail : AppCompatActivity() {

    private var brewery: SingleBrewery? = null
    private val viewModel: SavedBreweryViewModel by viewModels()

    private var isBookmarked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery_detail)

        if (intent != null && intent.hasExtra(EXTRA_SINGLE_BREWERY)) {
            brewery = intent.getSerializableExtra(EXTRA_SINGLE_BREWERY) as SingleBrewery

            findViewById<TextView>(R.id.tv_brewery_name).text = brewery!!.name
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_brewery_detail, menu)

        val bookmarkItem = menu.findItem(R.id.action_bookmark)
        viewModel.getBreweryByName(brewery!!.id).observe(this) { bookmarkedBrewery ->
            when (bookmarkedBrewery) {
                null -> {
                    isBookmarked = false
                    bookmarkItem?.isChecked = false
                    bookmarkItem?.icon = AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_action_bookmark_off
                    )
                }
                else -> {
                    isBookmarked = true
                    bookmarkItem?.isChecked = true
                    bookmarkItem?.icon = AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_action_bookmark_on
                    )
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_bookmark -> {
                toggleRepoBookmark(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleRepoBookmark(menuItem: MenuItem) {
        when (isBookmarked) {
            false -> viewModel.addBrewery(brewery!!)
            true ->  viewModel.removeBrewery(brewery!!)
        }
    }
}