package com.example.egsassignment.presentation.features.movielist

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.R
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.presentation.base.BaseActivity
import com.example.egsassignment.presentation.features.moviedetail.MoveDetailActivity
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListActivity : BaseActivity(R.layout.activity_movie_list) {

    @Inject
    lateinit var viewModel: MovieListViewModel

    @Inject
    lateinit var useCase: RetrieveMovieDetailUseCase

    private val moviesAdapter = MoviesApdater {
        // navigate to movie detail
    }

    override fun doInject() {
        (application as MovieApplication).appComponent.movieListComponent()
            .create().inject(this)
    }

    override fun setupView() {
        with(rvMovies) {
            layoutManager = GridLayoutManager(this@MovieListActivity, 3)
            adapter = moviesAdapter
        }
    }

    override fun initialise() {
        // fetch data
    }

    override fun observeData() {
        viewModel.movies.observe(this) {
            moviesAdapter.submitList(emptyList())
            moviesAdapter.submitList(it)
        }

        viewModel.navigateToMovieDetail.observe(this) {
            MoveDetailActivity.start(this, it)
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            useCase.execute(RetrieveMovieDetailUseCase.Input(movieId = 1072790))
////                .flowOn(Dispatchers.IO)
                .onEach {
                    Log.d("xxx", "activity - onEach - thread=${Thread.currentThread().name}")
                }
                .collect {
                    Log.d("xxx", "activity - collect - thread=${Thread.currentThread().name}")
                }
        }
    }
}