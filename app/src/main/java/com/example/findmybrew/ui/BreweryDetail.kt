package com.example.findmybrew.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.findmybrew.R

const val EXTRA_SINGLE_BREWERY = "com.example.android.findmybrew.SINGLE_BREWERY"

class BreweryDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery_detail)
    }
}