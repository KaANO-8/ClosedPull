package com.kaano8.closedpull.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaano8.closedpull.R
import com.kaano8.closedpull.databinding.ClosedPrListItemBinding
import com.kaano8.closedpull.ui.main.adapter.data.ClosedPrUiModel
import com.kaano8.closedpull.util.DateFormatter

class ClosedPrViewHolder(private val binding: ClosedPrListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ClosedPrUiModel) {
        with(binding) {
            Glide
                .with(itemView)
                .load(item.imageUrl)
                .fitCenter()
                .override(200, 200)
                // A dummy image shown until actual one is loaded.
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(userImageView)

            userNameTextView.text = itemView.context.getString(R.string.username).format(item.username)
            titleTextView.text = item.title
            creationDate.text = itemView.context.getString(R.string.creation_date).format(DateFormatter.formatDate(item.creationDate))
            closedDate.text = itemView.context.getString(R.string.closed_date).format(DateFormatter.formatDate(item.closedDate))
        }

    }

    companion object {
        fun create(parent: ViewGroup): ClosedPrViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.closed_pr_list_item, parent, false)
            val binding = ClosedPrListItemBinding.bind(view)
            return ClosedPrViewHolder(binding)
        }
    }
}