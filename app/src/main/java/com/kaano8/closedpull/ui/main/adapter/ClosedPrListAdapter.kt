package com.kaano8.closedpull.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kaano8.closedpull.ui.main.adapter.data.ClosedPrUiModel
import javax.inject.Inject

class ClosedPrListAdapter @Inject constructor() :
    ListAdapter<ClosedPrUiModel, ClosedPrViewHolder>(MainDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosedPrViewHolder {
        return ClosedPrViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ClosedPrViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Diff util helps to identify min changes required to make old list to new list.
     * This helps to improve performance, as well as provide a nice animation.
     */
    private class MainDiffCallback : DiffUtil.ItemCallback<ClosedPrUiModel>() {
        override fun areItemsTheSame(oldItem: ClosedPrUiModel, newItem: ClosedPrUiModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ClosedPrUiModel, newItem: ClosedPrUiModel): Boolean =
            oldItem == newItem
    }
}
