package com.example.egsassignment.presentation.features.movielist

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.R
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.presentation.base.BaseActivity
import com.example.egsassignment.presentation.features.moviedetail.MovieDetailActivity
import com.example.egsassignment.service.MoviesService
import com.example.egsassignment.utils.GridItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListActivity : BaseActivity(R.layout.activity_movie_list) {

    @Inject
    lateinit var viewModel: MovieListContract.ViewModel

    @Inject
    lateinit var useCase: RetrieveMovieDetailUseCase

    private var service: MoviesService? = null

    private var serviceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d("Phillip", "onServiceConnected")
            val myBinder = binder as? MoviesService.MoviesBinder
            service = myBinder?.getService()
            serviceBound = true

            lifecycleScope.launch(Dispatchers.Main) {
                service?.retrieveMovieList()?.collect { data ->
                    viewModel.onMoviesRetrieved(data)
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("Phillip", "onServiceDisconnected")
            serviceBound = false
        }
    }

    private val moviesAdapter = MoviesApdater {
        viewModel.onMovieItemSelected(it)
    }

    override fun doInject() {
        (application as MovieApplication).appComponent.movieListComponent()
            .create().inject(this)
    }

    override fun setupView() {
        with(rvMovies) {
            layoutManager = GridLayoutManager(this@MovieListActivity, 3).apply {
                spanSizeLookup = (object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (moviesAdapter.getItemViewType(position)) {
                            MovieListViewHolder.ViewType.HEADER.ordinal -> 3
                            else -> 1
                        }
                    }
                })
            }
            addItemDecoration(
                GridItemOffsetDecoration(
                    context = this@MovieListActivity,
                    itemOffsetId = R.dimen.dp1,
                    spanCount = 3,
                    includeEdge = false
                )
            )
            adapter = moviesAdapter
        }
    }

    override fun initialise() {
        // do nothing
    }

    override fun onStart() {
        super.onStart()
        if (!serviceBound) {
            Log.d("Phillip", "start bindService")
            val serviceIntent = Intent(this, MoviesService::class.java)
            bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun observeData() {
        viewModel.movies.observe(this) {
            Log.d("Phillip", "receive data")
            moviesAdapter.submitList(emptyList())
            moviesAdapter.submitList(it)
        }

        viewModel.navigateToMovieDetail.observe(this) {
            MovieDetailActivity.start(this, it)
        }
    }

    override fun onStop() {
        super.onStop()
        if (serviceBound) {
            Log.d("Phillip", "unbindService")
            unbindService(serviceConnection)
            serviceBound = false
        }
    }

}