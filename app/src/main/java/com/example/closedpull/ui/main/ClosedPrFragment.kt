package com.example.closedpull.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.closedpull.databinding.FragmentClosedPrBinding
import com.example.closedpull.ui.main.adapter.ClosedPrListAdapter
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
    }

    private fun setupRecyclerView() {
        closedPrBinding.closedPrRecyclerView.adapter = adapter
        adapter.submitList(viewModel.provideDummyList())
    }
}
