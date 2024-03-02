package com.example.egsassignment.presentation.features.moviedetail

import android.content.Context
import android.content.Intent
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.R
import com.example.egsassignment.domain.model.moviedetail.MovieDetails
import com.example.egsassignment.presentation.base.BaseActivity

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
class MoveDetailActivity : BaseActivity(R.layout.activity_movie_detail) {

    override fun doInject() {
        (application as MovieApplication).appComponent.inject(this)
    }

    override fun setupView() {
        TODO("Not yet implemented")
    }

    override fun initialise() {
        TODO("Not yet implemented")
    }

    override fun observeData() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun start(context: Context, movieId: Int) {
            context.startActivity(
                Intent(context, MoveDetailActivity::class.java).putExtra(MOVIE_ID, movieId)
            )
        }
    }
}