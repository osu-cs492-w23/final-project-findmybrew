package com.example.findmybrew.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findmybrew.R
import com.example.findmybrew.data.LoadingStatus
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

        val searchBoxET: EditText = findViewById(R.id.et_search_box)
        val searchBtn: Button = findViewById(R.id.btn_search)

        loadingErrorTV = findViewById(R.id.tv_loading_error)
        loadingIndicator = findViewById(R.id.loading_indicator)

        breweryListRV = findViewById(R.id.rv_brewery_list)
        breweryListRV.layoutManager = LinearLayoutManager(this)
        breweryListRV.setHasFixedSize(true)
        breweryListRV.adapter = breweryAdapter

        viewModel.brewery.observe(this) { brewery ->
            breweryAdapter.updateBrewery(brewery)
        }

        viewModel.error.observe(this) { error ->
            if (error != null) {
                loadingErrorTV.text = getString(R.string.loading_error, error.message)
                loadingErrorTV.visibility = View.VISIBLE
                Log.e("BrewerySearch", "Error fetching brewery data: ${error.message}")
            }
        }

        viewModel.loading.observe(this) { loading ->
            when (loading) {
                LoadingStatus.LOADING -> {
                    loadingIndicator.visibility = View.VISIBLE
                    breweryListRV.visibility = View.INVISIBLE
                    loadingErrorTV.visibility = View.INVISIBLE
                }
                LoadingStatus.ERROR -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    breweryListRV.visibility = View.INVISIBLE
                    loadingErrorTV.visibility = View.VISIBLE
                }
                else -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    breweryListRV.visibility = View.VISIBLE
                    loadingErrorTV.visibility = View.INVISIBLE
                }
            }
        }

        searchBtn.setOnClickListener {
            val searchQuery = searchBoxET.text.toString()
            if (!TextUtils.isEmpty(searchQuery)) {
                viewModel.loadBrewerySearch(searchQuery)
                breweryListRV.scrollToPosition(0)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun onBreweryItemClick(brewery: SingleBrewery) {
        val intent = Intent(this, BreweryDetail::class.java).apply {
            putExtra(EXTRA_SINGLE_BREWERY, brewery)
        }
        startActivity(intent)
    }
}