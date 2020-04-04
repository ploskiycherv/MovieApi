package com.example.movieapi.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movieapi.R

class MovieItemViewHolder(itemView: View) : ViewHolder(itemView) {
    @JvmField
    var titleTextView: TextView
    @JvmField
    var yearTextView: TextView
    @JvmField
    var ratingTextView: TextView
    @JvmField
    var posterImageView: ImageView
    @JvmField
    var itemCardView: CardView

    init {
        titleTextView = itemView.findViewById(R.id.titleTextView)
        yearTextView = itemView.findViewById(R.id.yearTextView)
        ratingTextView = itemView.findViewById(R.id.ratingTextView)
        posterImageView = itemView.findViewById(R.id.posterImageView)
        itemCardView = itemView.findViewById(R.id.itemCardView)
    }
}
