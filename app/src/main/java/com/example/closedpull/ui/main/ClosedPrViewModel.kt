package com.example.closedpull.ui.main

import androidx.lifecycle.ViewModel
import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClosedPrViewModel @Inject constructor() : ViewModel() {

    fun provideDummyList(): List<ClosedPrUiModel> =
        listOf(
            ClosedPrUiModel(id = "1", username = "KaANO", title = "Test", creationDate = "20/02/2022", closedDate = "21/02/2022"),
            ClosedPrUiModel(id = "1", username = "KaANO", title = "Test", creationDate = "20/02/2022", closedDate = "21/02/2022"),
            ClosedPrUiModel(id = "1", username = "KaANO", title = "Test", creationDate = "20/02/2022", closedDate = "21/02/2022")
        )
}