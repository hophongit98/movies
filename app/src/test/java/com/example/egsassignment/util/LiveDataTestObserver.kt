package com.example.egsassignment.util

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class LiveDataTestObserver<T>(liveData: LiveData<T>) : Observer<T> {
    val observedValues = mutableListOf<T?>()

    init {
        liveData.observeForever(this)
    }

    override fun onChanged(value: T) {
        observedValues.add(value)
    }
}
