package com.example.egsassignment.presentation.features.movielist

import androidx.recyclerview.widget.DiffUtil
import com.example.egsassignment.presentation.features.movielist.MovieListViewHolder.DisplayItem

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
class MovieListDiffCallback : DiffUtil.ItemCallback<DisplayItem>() {
    override fun areItemsTheSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return if (oldItem is DisplayItem.Movie && newItem is DisplayItem.Movie) {
            oldItem.movie.id == newItem.movie.id
        } else {
            oldItem.viewType == newItem.viewType
        }
    }

    override fun areContentsTheSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return oldItem == newItem
    }
}