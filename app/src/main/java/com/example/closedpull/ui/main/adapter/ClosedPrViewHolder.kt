package com.example.closedpull.ui.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.closedpull.R
import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel
import com.example.closedpull.util.DateFormatter

class ClosedPrViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val userImage = itemView.findViewById<ImageView>(R.id.userImageView)
    private val userName = itemView.findViewById<TextView>(R.id.userNameTextView)
    private val title = itemView.findViewById<TextView>(R.id.titleTextView)
    private val creationDate = itemView.findViewById<TextView>(R.id.creationDate)
    private val closedDate = itemView.findViewById<TextView>(R.id.closedDate)

    fun bind(item: ClosedPrUiModel) {
        Glide
            .with(itemView)
            .load(item.imageUrl)
            .fitCenter()
            .override(200,200)
            .placeholder(com.bumptech.glide.R.drawable.abc_spinner_textfield_background_material)
            .into(userImage)

        userName.text = itemView.context.getString(R.string.username).format(item.username)
        title.text = item.title
        creationDate.text =
            itemView.context.getString(R.string.creation_date).format(DateFormatter.formatDate(item.creationDate))
        closedDate.text = itemView.context.getString(R.string.closed_date).format(DateFormatter.formatDate(item.closedDate))
    }
}