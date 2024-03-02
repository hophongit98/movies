package com.example.egsassignment.presentation.features.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.egsassignment.R

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
class MoviesApdater(
    private val onMovieSelected: (Int) -> Unit
) :
    ListAdapter<MovieListViewHolder.DisplayItem, MovieListViewHolder>(MovieListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return createViewHolderByViewType(parent, viewType)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    private fun createViewHolderByViewType(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return when (viewType) {
            MovieListViewHolder.ViewType.HEADER.ordinal -> {
                MovieListHeaderViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie_list_header, parent, false)
                )
            }
            MovieListViewHolder.ViewType.MOVIE_CONTENT.ordinal -> {
                MovieListMovieContent(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie_list_movie_content, parent, false),
                    onMovieSelected
                )
            }
            else -> error("Does not support viewType$viewType")
        }
    }
}