package com.example.egsassignment.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before

/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
@ExperimentalCoroutinesApi
open class BaseTest {
    protected val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUpDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }
}