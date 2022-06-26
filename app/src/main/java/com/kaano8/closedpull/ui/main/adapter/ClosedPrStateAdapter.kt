package com.kaano8.closedpull.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaano8.closedpull.R
import com.kaano8.closedpull.extensions.gone
import com.kaano8.closedpull.extensions.visible
import javax.inject.Inject

class ClosedPrStateAdapter @Inject constructor() :
    LoadStateAdapter<ClosedPrStateAdapter.LoadStateViewHolder>() {

    private lateinit var retryAction: () -> Unit

    fun setRetryAction(retryAction: () -> Unit) {
        this.retryAction = retryAction
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retryAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.load_state_view, parent, false)
        )
    }

    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val errorMessageTextView = view.findViewById<TextView>(R.id.loadStateErrorMessage)
        private val progressBar = view.findViewById<ProgressBar>(R.id.loadStateProgressBar)
        private val retryButton = view.findViewById<Button>(R.id.retryButton)

        fun bind(loadState: LoadState, retryAction: () -> Unit) {

            if (loadState is LoadState.Error) {
                errorMessageTextView.text = loadState.error.message
            }

            if (loadState is LoadState.Loading) {
                progressBar.visible()
                errorMessageTextView.gone()
                retryButton.gone()
            } else {
                progressBar.gone()
                errorMessageTextView.visible()
                retryButton.visible()
            }

            retryButton.setOnClickListener { retryAction() }
        }
    }
}
