package com.kaano8.closedpull.repository

import com.kaano8.closedpull.data.ClosedPrDataModel

interface ClosedPrRepository {
    suspend fun getClosedPrs(): List<ClosedPrDataModel>
}
