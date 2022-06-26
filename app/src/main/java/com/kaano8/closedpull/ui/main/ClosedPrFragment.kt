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
import com.kaano8.closedpull.databinding.FragmentClosedPrBinding
import com.kaano8.closedpull.extensions.gone
import com.kaano8.closedpull.extensions.visible
import com.kaano8.closedpull.ui.main.adapter.list.ClosedPrListAdapter
import com.kaano8.closedpull.ui.main.adapter.loadstate.ClosedPrStateAdapter
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
        startCollection()
        observeLoadStates()
    }

    private fun setupRecyclerView() {
        with(closedPrBinding) {
            swipeRefreshLayout.setOnRefreshListener { closedPrListAdapter.refresh() }

            closedPrRecyclerView.apply {
                adapter = closedPrListAdapter
                // Optimization param stating that size of item will remain same throughout
                setHasFixedSize(true)
            }

            closedPrListAdapter.withLoadStateHeaderAndFooter(
                header = ClosedPrStateAdapter(closedPrListAdapter::retry),
                footer = ClosedPrStateAdapter(closedPrListAdapter::retry)
            )
        }
    }

    private fun startCollection() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                closedPrListAdapter.submitData(pagingData)
            }
        }
    }

    private fun observeLoadStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            closedPrListAdapter.loadStateFlow.collectLatest { loadStates ->
                // Need to identify the initial state, otherwise it will be shown at 2 places
                when(loadStates.source.refresh) {
                    is LoadState.NotLoading -> {
                       hideProgress()
                    }
                    is LoadState.Loading -> {
                        if (!closedPrBinding.swipeRefreshLayout.isRefreshing)
                            closedPrBinding.progressBar.visible()
                    }
                    is LoadState.Error -> {
                        hideProgress()
                        Toast.makeText(context, (loadStates.source.refresh as? LoadState.Error)?.error?.message ?: "", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun hideProgress() {
        if (closedPrBinding.swipeRefreshLayout.isRefreshing)
            closedPrBinding.swipeRefreshLayout.isRefreshing = false
        else
            closedPrBinding.progressBar.gone()
    }
}
