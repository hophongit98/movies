package com.example.egsassignment.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
@ExperimentalCoroutinesApi
open class ViewModelBaseTest : BaseTest() {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
}