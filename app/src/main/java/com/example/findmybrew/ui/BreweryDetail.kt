package com.example.findmybrew.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.example.findmybrew.R
import com.example.findmybrew.data.SingleBrewery

const val EXTRA_SINGLE_BREWERY = "com.example.android.findmybrew.SINGLE_BREWERY"

class BreweryDetail : AppCompatActivity() {

    private var brewery: SingleBrewery? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery_detail)

        if (intent != null && intent.hasExtra(EXTRA_SINGLE_BREWERY)) {
            brewery = intent.getSerializableExtra(EXTRA_SINGLE_BREWERY) as SingleBrewery

            findViewById<TextView>(R.id.tv_brewery_name).text = brewery!!.name
        }
    }
}