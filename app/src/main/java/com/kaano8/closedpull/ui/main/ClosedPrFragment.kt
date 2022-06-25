package com.kaano8.closedpull.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kaano8.closedpull.databinding.FragmentClosedPrBinding
import com.kaano8.closedpull.extensions.gone
import com.kaano8.closedpull.extensions.visible
import com.kaano8.closedpull.ui.main.adapter.ClosedPrListAdapter
import com.kaano8.closedpull.ui.main.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ClosedPrFragment : Fragment() {

    companion object {
        fun newInstance() = ClosedPrFragment()
    }

    private lateinit var closedPrBinding: FragmentClosedPrBinding

    private val viewModel: ClosedPrViewModel by viewModels()

    @Inject
    lateinit var adapter: ClosedPrListAdapter

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
        viewModel.getClosedPrs()
    }

    private fun setupRecyclerView() {
        closedPrBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getClosedPrs()
        }
        closedPrBinding.closedPrRecyclerView.adapter = adapter
    }

    private fun observeForEvents() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiStatus ->
            when (uiStatus) {
                is UiState.NoClosedPrs -> {
                    closedPrBinding.apply {
                        setSwipeRefreshingFalse()
                        progressBar.gone()
                        noClosedPrs.visible()
                        closedPrRecyclerView.gone()
                    }
                }
                is UiState.Loading -> {
                    closedPrBinding.apply {
                        if (!swipeRefreshLayout.isRefreshing)
                            progressBar.visible()
                        closedPrRecyclerView.gone()
                        noClosedPrs.gone()
                    }
                }
                is UiState.Success -> {
                    adapter.submitList(uiStatus.closedPrList)
                    closedPrBinding.apply {
                        setSwipeRefreshingFalse()
                        progressBar.gone()
                        closedPrRecyclerView.visible()
                        noClosedPrs.gone()
                    }
                }
                is UiState.Error -> {
                    closedPrBinding.apply {
                        setSwipeRefreshingFalse()
                        progressBar.gone()
                        closedPrRecyclerView.gone()
                        noClosedPrs.gone()
                    }
                    Toast.makeText(
                        context,
                        uiStatus.exceptionMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setSwipeRefreshingFalse() {
        with(closedPrBinding.swipeRefreshLayout) {
            if (isRefreshing)
                isRefreshing = false
        }


    }
}
