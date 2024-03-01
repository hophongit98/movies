package com.example.egsassignment.presentation.features.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.egsassignment.MovieApplication

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
class MoveDetailActivity : AppCompatActivity() {

    // MovieDBService

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MovieApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}