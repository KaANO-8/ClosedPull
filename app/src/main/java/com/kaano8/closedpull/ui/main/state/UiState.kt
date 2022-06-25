package com.kaano8.closedpull.ui.main.state

import com.kaano8.closedpull.ui.main.adapter.data.ClosedPrUiModel

sealed class UiState {
    object Loading : UiState()
    data class Success(val closedPrList: List<ClosedPrUiModel>) : UiState()
    data class Error(val exceptionMessage: String) : UiState()
}
