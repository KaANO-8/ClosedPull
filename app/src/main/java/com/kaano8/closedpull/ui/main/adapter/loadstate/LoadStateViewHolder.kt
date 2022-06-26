package com.kaano8.closedpull.ui.main.adapter.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.kaano8.closedpull.R
import com.kaano8.closedpull.databinding.LoadStateViewBinding

class LoadStateViewHolder(parent: ViewGroup, retry: () -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.load_state_view, parent, false)
) {
    private val binding = LoadStateViewBinding.bind(itemView)
    private val progressBar: ProgressBar = binding.loadStateProgressBar
    private val errorMsg: TextView = binding.loadStateErrorMessage
    private val retry: Button = binding.retryButton
        .also {
            it.setOnClickListener { retry() }
        }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }

        progressBar.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = loadState is LoadState.Error
    }
}
