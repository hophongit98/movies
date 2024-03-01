package com.example.egsassignment.presentation.features.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.egsassignment.MovieApplication
import com.example.egsassignment.R
import com.example.egsassignment.domain.usecase.RetrieveMovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var useCase: RetrieveMovieDetailUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MovieApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
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