package com.example.egsassignment.presentation.features.moviedetail

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.R
import com.example.egsassignment.databinding.ActivityMovieDetailBinding
import com.example.egsassignment.presentation.base.BaseActivity
import com.example.egsassignment.service.MoviesService
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Phillip Truong
 * date 28/02/2024.
 */
class MovieDetailActivity : BaseActivity(R.layout.activity_movie_detail) {

    @Inject
    lateinit var viewModel: MovieDetailContract.ViewModel

    private lateinit var binding: ActivityMovieDetailBinding

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

    override fun doInject() {
        (application as MovieApplication).appComponent.movieDetailComponent().create().inject(this)
    }

    override fun setupView() {
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initialise() {
        val movieId = intent?.getIntExtra(MOVIE_ID, 0) ?: 0
        viewModel.initialise(movieId)
        if (!serviceBound) {
            Log.d("Phillip", "start bindService")
            val serviceIntent = Intent(this, MoviesService::class.java)
            bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun observeData() {
        viewModel.loadMovieDetail.observe(this) { id ->
            if (!serviceBound) {
                lifecycleScope.launch(Dispatchers.Main) {
                    service?.retrieveMovieDetails(id)?.collect { data ->
                        viewModel.onMovieDetailRetrieved(data)
                    }
                }
            }
        }

        viewModel.movie.observe(this) {
            with(binding) {
                Glide.with(this@MovieDetailActivity)
                    .load(getString(R.string.backdrop_url, it.backDrop))
                    .apply(RequestOptions())
                    .into(ivBackdrop)

                tvTitle.text = if (it.year != null) {
                    getString(R.string.movie_detail_title, it.title, it.year)
                } else {
                    it.title
                }
                it.releaseDate?.let { date ->
                    tvReleaseDate.text = date
                }
                it.duration?.let { time ->
                    tvDuration.text = time
                }
                tvGenres.isVisible = it.genres.isNotEmpty()
                tvGenres.text = it.genres

                tvTagLine.isVisible = it.tagLine.isNotEmpty()
                tvTagLine.text = it.tagLine

                tvOverview.isVisible = it.overview.isNotEmpty()
                tvOverview.text = it.overview
                tvOverviewTitle.isVisible = it.overview.isNotEmpty()
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


    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun start(context: Context, movieId: Int) {
            context.startActivity(
                Intent(context, MovieDetailActivity::class.java).putExtra(MOVIE_ID, movieId)
            )
        }
    }
}