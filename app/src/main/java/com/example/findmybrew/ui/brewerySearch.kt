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

class brewerySearch : AppCompatActivity() {
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

        viewModel.forecast.observe(this) { brewery ->
            if (brewery != null) {
                breweryAdapter.updateBrewery(brewery)
                breweryListRV.visibility = View.VISIBLE
                breweryListRV.scrollToPosition(0)
            }
        }

        /*
         * Set up an observer on the error associated with the current API call.  If the error is
         * not null, display the error that occurred in the UI.
         */
        viewModel.error.observe(this) { error ->
            if (error != null) {
                loadingErrorTV.text = getString(R.string.loading_error, error.message)
                loadingErrorTV.visibility = View.VISIBLE
                Log.e("BrewerySearch", "Error fetching forecast: ${error.message}")
            }
        }

        /*
         * Set up an observer on the loading status of the API query.  Display the correct UI
         * elements based on the current loading status.
         */
        viewModel.loading.observe(this) { loading ->
            if (loading) {
                loadingIndicator.visibility = View.VISIBLE
                loadingErrorTV.visibility = View.INVISIBLE
                breweryListRV.visibility = View.INVISIBLE
            } else {
                loadingIndicator.visibility = View.INVISIBLE
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