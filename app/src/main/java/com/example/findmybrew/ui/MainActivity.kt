package com.example.findmybrew.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.findmybrew.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val breweryButton : Button = findViewById(R.id.breweryButton)
        val beerButton : Button = findViewById(R.id.beerButton)

        breweryButton.setOnClickListener(){
            val intent = Intent(this, BrewerySearch::class.java )
            startActivity(intent)
        }

        beerButton.setOnClickListener(){
            val intent = Intent(this, BeerSearch::class.java)
            startActivity(intent)
        }
    }
}