package com.example.egsassignment.presentation.features.movielist

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Phillip Truong
 * date 03/03/2024.
 */
class CustomRecyclerviewOnScrollListener(
    private val layoutManager: GridLayoutManager,
    private val onLoadMoreListener: MovieListContract.OnLoadMoreListener,
) :
    RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var itemCounts = 0
    private var isLoading = true
    private val remainingThreshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        itemCounts = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        if (isLoading && itemCounts > previousTotal) {
            previousTotal = itemCounts
            isLoading = false
        }

        val lastVisibleRow = layoutManager.spanSizeLookup.getSpanGroupIndex(lastVisibleItemPosition,
            layoutManager.spanCount)
        val totalRows = layoutManager.spanSizeLookup.getSpanGroupIndex(itemCounts - 1,
            layoutManager.spanCount)

        if (!isLoading && lastVisibleRow >= totalRows - remainingThreshold) {
            onLoadMoreListener.onLoadMore()
            isLoading = true
        }
    }

}