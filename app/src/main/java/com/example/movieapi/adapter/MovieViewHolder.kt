package com.example.movieapi.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movieapi.R

class MovieItemViewHolder(itemView: View) : ViewHolder(itemView) {

    @JvmField
    var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

    @JvmField
    var yearTextView: TextView = itemView.findViewById(R.id.yearTextView)

    @JvmField
    var ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)

    @JvmField
    var posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)

    @JvmField
    var itemCardView: CardView = itemView.findViewById(R.id.itemCardView)

}
