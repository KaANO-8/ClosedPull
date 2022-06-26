package com.kaano8.closedpull.ui.main.adapter.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaano8.closedpull.R
import com.kaano8.closedpull.ui.main.adapter.data.ClosedPrUiModel
import com.kaano8.closedpull.util.DateFormatter

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
            // A dummy image shown until actual one is loaded.
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(userImage)

        userName.text = itemView.context.getString(R.string.username).format(item.username)
        title.text = item.title
        creationDate.text =
            itemView.context.getString(R.string.creation_date).format(DateFormatter.formatDate(item.creationDate))
        closedDate.text = itemView.context.getString(R.string.closed_date).format(DateFormatter.formatDate(item.closedDate))
    }
}