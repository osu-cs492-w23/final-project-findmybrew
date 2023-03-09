package com.example.findmybrew.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findmybrew.R
import com.example.findmybrew.data.PhotoOfBeer

class BeerAdapter() : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {
    var beers: List<PhotoOfBeer> = listOf()

    fun updateBrewery(beer: List<PhotoOfBeer>?) {
        beers = beer ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.beers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.brewery_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.beers[position])
    }

    class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.tv_beer_name)

        private lateinit var currentBeer: PhotoOfBeer

        init {

        }

        fun bind(beer: PhotoOfBeer) {
            currentBeer = beer

            nameTV.text = currentBeer.name
        }
    }
}