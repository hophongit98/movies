package com.example.egsassignment.presentation.features.moviedetail

import com.example.egsassignment.MovieApplication
import com.example.egsassignment.R
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
}