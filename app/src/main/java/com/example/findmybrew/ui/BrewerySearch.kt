package com.example.findmybrew.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findmybrew.R
import com.example.findmybrew.data.SingleBrewery
import com.google.android.material.progressindicator.CircularProgressIndicator

class BrewerySearch : AppCompatActivity() {
    private val viewModel: BreweryViewModel by viewModels()
    private val breweryAdapter = BreweryAdapter(::onBreweryItemClick)

    private lateinit var breweryListRV: RecyclerView
    private lateinit var loadingErrorTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brewery_search)

        loadingErrorTV = findViewById(R.id.tv_loading_error)
        loadingIndicator = findViewById(R.id.loading_indicator)

        breweryListRV = findViewById(R.id.rv_brewery_list)
        breweryListRV.layoutManager = LinearLayoutManager(this)
        breweryListRV.setHasFixedSize(true)
        breweryListRV.adapter = breweryAdapter

        viewModel.brewery.observe(this) { brewery ->
            if (brewery != null) {
                breweryAdapter.updateBrewery(brewery)
                breweryListRV.visibility = View.VISIBLE
                breweryListRV.scrollToPosition(0)
            }
        }

        viewModel.error.observe(this) { error ->
            if (error != null) {
                loadingErrorTV.text = getString(R.string.loading_error, error.message)
                loadingErrorTV.visibility = View.VISIBLE
                Log.e("BrewerySearch", "Error fetching forecast: ${error.message}")
            }
        }

        viewModel.loading.observe(this) { loading ->
            if (loading) {
                loadingIndicator.visibility = View.VISIBLE
                loadingErrorTV.visibility = View.INVISIBLE
                breweryListRV.visibility = View.INVISIBLE
            } else {
                loadingIndicator.visibility = View.INVISIBLE
            }
        }

    viewModel.loadBrewerySearch("Block")
    }

    private fun onBreweryItemClick(brewery: SingleBrewery) {
        val intent = Intent(this, BreweryDetail::class.java).apply {
            putExtra(EXTRA_SINGLE_BREWERY, brewery)
        }
        startActivity(intent)
    }
}