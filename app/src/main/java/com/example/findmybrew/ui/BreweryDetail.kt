package com.example.findmybrew.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.findmybrew.R
import com.example.findmybrew.data.SingleBrewery

const val EXTRA_SINGLE_BREWERY = "com.example.android.findmybrew.SINGLE_BREWERY"

class BreweryDetail : AppCompatActivity() {

    private var brewery: SingleBrewery? = null
    private val viewModel: PhotosOfBeersViewModel by viewModels()
    private lateinit var photoOfBeersRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery_detail)

        if (intent != null && intent.hasExtra(EXTRA_SINGLE_BREWERY)) {
            brewery = intent.getSerializableExtra(EXTRA_SINGLE_BREWERY) as SingleBrewery

            findViewById<TextView>(R.id.tv_brewery_name).text = brewery!!.name

            viewModel.beer.observe(this) {

            }
        }
    }
}