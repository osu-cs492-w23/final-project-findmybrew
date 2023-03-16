package com.example.findmybrew.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findmybrew.R
import com.example.findmybrew.data.SingleBrewery

class BreweryAdapter(private val onClick: (SingleBrewery) -> Unit) : RecyclerView.Adapter<BreweryAdapter.ViewHolder>() {
    var breweries: List<SingleBrewery> = listOf()

    fun updateBrewery(brewery: List<SingleBrewery>?) {
        breweries = brewery ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.breweries.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.brewery_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.breweries[position])
    }

    class ViewHolder(itemView: View, val onClick: (SingleBrewery) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.tv_brewery_name)
        private val cityTV: TextView = itemView.findViewById(R.id.tv_brewery_location)

        private lateinit var currentBrewery: SingleBrewery

        /*
         * Set up a click listener on this individual ViewHolder.  Call the provided onClick
         * function, passing the forecast item represented by this ViewHolder as an argument.
         */
        init {
            itemView.setOnClickListener {
                currentBrewery.let(onClick)
            }
        }

        fun bind(brewery: SingleBrewery) {
            currentBrewery = brewery
            nameTV.text = currentBrewery.name
            cityTV.text = currentBrewery.city + ", " + currentBrewery.state
        }
    }
}