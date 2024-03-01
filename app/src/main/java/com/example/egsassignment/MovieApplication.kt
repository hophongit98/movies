package com.example.egsassignment

import android.app.Application
import com.example.egsassignment.di.AppComponent
import com.example.egsassignment.di.DaggerAppComponent

/**
 * Created by Phillip Truong
 * date 29/02/2024.
 */
class MovieApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: Application? = null

        fun getInstance(): Application {
            return instance ?: throw IllegalStateException("Application instance not initialized")
        }
    }
}