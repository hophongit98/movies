package com.example.egsassignment.presentation.features.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.R

class MovieListActivity : AppCompatActivity() {

    // MovieDBService

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MovieApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}