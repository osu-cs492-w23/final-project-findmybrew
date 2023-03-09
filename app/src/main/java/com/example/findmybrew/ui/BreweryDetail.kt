package com.example.findmybrew.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findmybrew.R
import com.example.findmybrew.data.PhotoOfBeer
import com.example.findmybrew.data.SingleBrewery

const val EXTRA_SINGLE_BREWERY = "com.example.android.findmybrew.SINGLE_BREWERY"

class BreweryDetail : AppCompatActivity() {

    private var brewery: SingleBrewery? = null
    private val viewModel: PhotosOfBeersViewModel by viewModels()
    private val beerAdapter = BeerAdapter()
    private lateinit var photoOfBeersRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery_detail)

        photoOfBeersRV = findViewById(R.id.rv_brewery_list)
        photoOfBeersRV.layoutManager = LinearLayoutManager(this)
        photoOfBeersRV.setHasFixedSize(true)
        photoOfBeersRV.adapter = beerAdapter

        if (intent != null && intent.hasExtra(EXTRA_SINGLE_BREWERY)) {
            brewery = intent.getSerializableExtra(EXTRA_SINGLE_BREWERY) as SingleBrewery

            findViewById<TextView>(R.id.tv_brewery_name).text = brewery!!.name


            viewModel.beer.observe(this) {
                beerAdapter.updateBeer(it)

                findViewById<TextView>(R.id.tv_brewery_street).text = brewery!!.street
                findViewById<TextView>(R.id.tv_brewery_city).text = brewery!!.city
                findViewById<TextView>(R.id.tv_brewery_state).text = brewery!!.state
                findViewById<TextView>(R.id.tv_brewery_postal).text = brewery!!.postal_code
                findViewById<TextView>(R.id.tv_brewery_url).text = brewery!!.website_url
            }
        }
    }
}