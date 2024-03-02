package com.example.egsassignment.presentation.features.movielist

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.egsassignment.R
import com.example.egsassignment.domain.model.movielist.MovieItem

/**
 * Created by Phillip Truong
 * date 02/03/2024.
 */
abstract class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: DisplayItem)

    sealed class DisplayItem(val viewType: Int) {
        data class Header(val title: String): DisplayItem(ViewType.HEADER.ordinal)
        data class Movie(val movie: MovieItem): DisplayItem(ViewType.MOVIE_CONTENT.ordinal)
    }

    enum class ViewType {
        HEADER,
        MOVIE_CONTENT
    }
}

class MovieListHeaderViewHolder(view: View) : MovieListViewHolder(view) {
    override fun bind(item: DisplayItem) {
        // Do nothing
    }
}

class MovieListMovieContent(view: View, private val onMovieSelected: (Int) -> Unit) : MovieListViewHolder(view) {
    val ivPoster = itemView.findViewById<AppCompatImageView>(R.id.ivMovieContentPoster)
    val tvMovieTitle = itemView.findViewById<AppCompatTextView>(R.id.tvMovieContentTitle)
    val tvMovieDate = itemView.findViewById<AppCompatTextView>(R.id.tvMovieContentDate)

    override fun bind(item: DisplayItem) = with(itemView) {
        if (item is DisplayItem.Movie) {

            Glide.with(this)
                .load(context.getString(R.string.poster_url, item.movie.posterPath))
                .apply(RequestOptions())
                .into(ivPoster)

            tvMovieTitle.text = item.movie.title
            tvMovieDate.text = item.movie.releaseDate

            setOnClickListener {
                onMovieSelected(item.movie.id)
            }
        }
    }

}