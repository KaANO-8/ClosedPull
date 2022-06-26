package com.kaano8.closedpull.repository

import com.kaano8.closedpull.api.data.ClosedPrDataModel
import java.io.IOException
import kotlin.jvm.Throws

interface ClosedPrRepository {
    @Throws(IOException::class)
    suspend fun getClosedPrs(): List<ClosedPrDataModel>
}
