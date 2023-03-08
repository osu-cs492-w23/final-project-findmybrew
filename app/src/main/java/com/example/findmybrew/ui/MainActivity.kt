package com.example.findmybrew.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
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

    override fun onResume() {
        super.onResume()
        val videoview = findViewById<View>(R.id.bg_video) as VideoView
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + com.example.findmybrew.R.raw.beerpouring)
        videoview.setVideoURI(uri)
        videoview.start()
        videoview.setOnPreparedListener { it.isLooping = true }
    }
}