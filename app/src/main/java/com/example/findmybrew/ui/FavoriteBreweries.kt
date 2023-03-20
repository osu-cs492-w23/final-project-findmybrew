package com.example.findmybrew.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findmybrew.R
import com.example.findmybrew.data.SingleBrewery

class FavoriteBreweries : AppCompatActivity() {
    private val viewModel: SavedBreweryViewModel by viewModels()
    private val breweryAdapter = BreweryAdapter(::onBreweryItemClick)

    private lateinit var savedBreweryListRV : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_breweries)
        savedBreweryListRV = findViewById(R.id.rv_brewery_list)
        savedBreweryListRV.layoutManager = LinearLayoutManager(this)
        savedBreweryListRV.setHasFixedSize(true)
        savedBreweryListRV.adapter = breweryAdapter

        viewModel.breweries.observe(this){breweries ->
            if (breweries != null){
                breweryAdapter.updateBrewery(breweries)
            }
        }
    }
    private fun onBreweryItemClick(brewery: SingleBrewery) {
        val intent = Intent(this, BreweryDetail::class.java).apply {
            putExtra(EXTRA_SINGLE_BREWERY, brewery)
        }
        startActivity(intent)
    }
}