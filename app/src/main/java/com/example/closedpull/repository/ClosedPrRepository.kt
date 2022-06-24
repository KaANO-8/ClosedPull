package com.example.closedpull.repository

import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel

interface ClosedPrRepository {
    fun getClosedPrs(): List<ClosedPrUiModel>
}