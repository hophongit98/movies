package com.example.egsassignment.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
abstract class BaseActivity(layoutId: Int): AppCompatActivity(layoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        doInject()
        super.onCreate(savedInstanceState)
        setupView()
        initialise()
        observeData()
    }

    abstract fun doInject()

    abstract fun setupView()

    abstract fun initialise()

    abstract fun observeData()
}