package com.kaano8.closedpull.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.kaano8.closedpull.databinding.FragmentClosedPrBinding
import com.kaano8.closedpull.extensions.gone
import com.kaano8.closedpull.extensions.visible
import com.kaano8.closedpull.ui.main.adapter.ClosedPrListAdapter
import com.kaano8.closedpull.ui.main.adapter.ClosedPrStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ClosedPrFragment : Fragment() {

    companion object {
        fun newInstance() = ClosedPrFragment()
    }

    private lateinit var closedPrBinding: FragmentClosedPrBinding

    private val viewModel: ClosedPrViewModel by viewModels()

    @Inject
    lateinit var closedPrListAdapter: ClosedPrListAdapter

    @Inject
    lateinit var closedPrStateAdapter: ClosedPrStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        closedPrBinding = FragmentClosedPrBinding.inflate(inflater, container, false)
        return closedPrBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeForEvents()
    }

    private fun setupRecyclerView() {
        with(closedPrBinding) {
            swipeRefreshLayout.setOnRefreshListener { closedPrListAdapter.refresh() }

            closedPrStateAdapter.setRetryAction { closedPrListAdapter.retry() }

            closedPrListAdapter.withLoadStateFooter(
                footer = closedPrStateAdapter
            )

            closedPrRecyclerView.apply {
                adapter = closedPrListAdapter
                // Optimization param stating that size of item will remain same throughout
                setHasFixedSize(true)
            }

/*            closedPrListAdapter.addLoadStateListener { loadState ->

                if (loadState.refresh is LoadState.Loading) {
                    if (!swipeRefreshLayout.isRefreshing)
                        progressBar.visible()
                } else {
                    if (swipeRefreshLayout.isRefreshing)
                        swipeRefreshLayout.isRefreshing = false
                    progressBar.gone()
                }

                // getting the error
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    Toast.makeText(context, it.error.message, Toast.LENGTH_LONG).show()
                }

            }*/
        }

    }

    private fun observeForEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                closedPrListAdapter.submitData(pagingData)
            }
        }
    }
}
