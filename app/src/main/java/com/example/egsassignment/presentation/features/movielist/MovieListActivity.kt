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
import com.example.egsassignment.databinding.ActivityMovieDetailBinding
import com.example.egsassignment.databinding.ActivityMovieListBinding
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import com.example.egsassignment.presentation.base.BaseActivity
import com.example.egsassignment.presentation.features.moviedetail.MovieDetailActivity
import com.example.egsassignment.service.MoviesService
import com.example.egsassignment.utils.GridItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListActivity : BaseActivity(R.layout.activity_movie_list),
    MovieListContract.OnLoadMoreListener {

    @Inject
    lateinit var viewModel: MovieListContract.ViewModel

    @Inject
    lateinit var useCase: RetrieveMovieDetailUseCase

    private lateinit var binding: ActivityMovieListBinding

    private var service: MoviesService? = null

    private var serviceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d("Phillip", "onServiceConnected")
            val myBinder = binder as? MoviesService.MoviesBinder
            service = myBinder?.getService()
            serviceBound = true
            viewModel.onServiceBound()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("Phillip", "onServiceDisconnected")
            serviceBound = false
        }
    }

    private val moviesAdapter = MoviesApdater {
        viewModel.onMovieItemSelected(it)
    }

    private var customRecyclerviewOnScrollListener: CustomRecyclerviewOnScrollListener? = null

    override fun doInject() {
        (application as MovieApplication).appComponent.movieListComponent()
            .create().inject(this)
    }

    override fun setupView() {
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val gridLayoutManager = GridLayoutManager(this@MovieListActivity, 3).apply {
            spanSizeLookup = (object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (moviesAdapter.getItemViewType(position)) {
                        MovieListViewHolder.ViewType.HEADER.ordinal -> 3
                        else -> 1
                    }
                }
            })
        }
        with(binding.rvMovies) {
            layoutManager = gridLayoutManager
            addItemDecoration(
                GridItemOffsetDecoration(
                    context = this@MovieListActivity,
                    itemOffsetId = R.dimen.dp1,
                    spanCount = 3,
                    includeEdge = false
                )
            )

            customRecyclerviewOnScrollListener =
                CustomRecyclerviewOnScrollListener(gridLayoutManager, this@MovieListActivity)
            addOnScrollListener(customRecyclerviewOnScrollListener!!)
            adapter = moviesAdapter
        }
    }

    override fun initialise() {
        if (!serviceBound) {
            Log.d("Phillip", "start bindService")
            val serviceIntent = Intent(this, MoviesService::class.java)
            bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun observeData() {
        viewModel.movies.observe(this) {
            Log.d("Phillip", "receive data movies=${it.size}")
            moviesAdapter.submitList(emptyList())
            moviesAdapter.submitList(it)
        }

        viewModel.navigateToMovieDetail.observe(this) {
            MovieDetailActivity.start(this, it)
        }

        viewModel.loadPage.observe(this) {
            Log.d("Phillip", "receive loadPage=$it")
            lifecycleScope.launch(Dispatchers.Main) {
                service?.retrieveMovieList(it)?.collect { data ->
                    viewModel.onMoviesRetrieved(data)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (serviceBound) {
            Log.d("Phillip", "unbindService")
            unbindService(serviceConnection)
            serviceBound = false
        }
    }

    override fun onLoadMore() {
        Log.d("xxx", "onLoadMore")
        viewModel.loadNextPage()
    }
}