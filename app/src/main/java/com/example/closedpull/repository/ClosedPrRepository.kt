package com.example.closedpull.repository

import com.example.closedpull.data.ClosedPrDataModel

interface ClosedPrRepository {
    suspend fun getClosedPrs(): List<ClosedPrDataModel>
}