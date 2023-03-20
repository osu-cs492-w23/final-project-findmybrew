package com.example.findmybrew.ui

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import com.google.android.material.snackbar.Snackbar

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
            findViewById<TextView>(R.id.tv_brewery_street).text = brewery!!.street
            findViewById<TextView>(R.id.tv_brewery_city).text = brewery!!.city + ", "
            findViewById<TextView>(R.id.tv_brewery_state).text = brewery!!.state + ", "
            findViewById<TextView>(R.id.tv_brewery_postal).text = brewery!!.postal_code
            findViewById<TextView>(R.id.tv_brewery_phone).text = brewery!!.phone
            findViewById<TextView>(R.id.tv_brewery_url).text = brewery!!.website_url
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
            R.id.action_map -> {
                viewForecastCityOnMap()
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

    private fun viewForecastCityOnMap() {
        val geoUri = Uri.parse(getString(
            R.string.geo_uri,
            brewery!!.lat!!.toDouble() ?: 0.0,
            brewery!!.lon!!.toDouble() ?: 0.0,
            20
        ))
        val intent = Intent(Intent.ACTION_VIEW, geoUri)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Snackbar.make(
                findViewById(R.id.constraint_layout),
                R.string.action_map_error,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}