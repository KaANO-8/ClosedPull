package com.example.closedpull.ui.main.state

import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel

sealed class UiStatus {
    object Loading : UiStatus()
    data class Success(val closedPrList: List<ClosedPrUiModel>) : UiStatus()
    data class Error(val exceptionMessage: String) : UiStatus()
}
