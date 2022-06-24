package com.example.closedpull.ui.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.closedpull.R
import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel

class ClosedPrViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

    private val userImage = itemView.findViewById<ImageView>(R.id.userImageView)
    private val userName = itemView.findViewById<TextView>(R.id.userNameTextView)
    private val title = itemView.findViewById<TextView>(R.id.titleTextView)
    private val creationDate = itemView.findViewById<TextView>(R.id.creationDate)
    private val closedDate = itemView.findViewById<TextView>(R.id.closedDate)

    fun bind(item: ClosedPrUiModel) {
        userName.text = itemView.context.getString(R.string.username).format(item.username)
        title.text = item.title
        creationDate.text = itemView.context.getString(R.string.creation_date).format(item.creationDate)
        closedDate.text = itemView.context.getString(R.string.closed_date).format(item.closedDate)
    }
}