package com.example.closedpull.ui.main

import androidx.lifecycle.ViewModel
import com.example.closedpull.repository.ClosedPrRepository
import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClosedPrViewModel @Inject constructor(private val repository: ClosedPrRepository) : ViewModel() {


    fun provideDummyList(): List<ClosedPrUiModel> =
        repository.getClosedPrs()
}