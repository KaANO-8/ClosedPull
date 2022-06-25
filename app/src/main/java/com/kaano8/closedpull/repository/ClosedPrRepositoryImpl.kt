package com.kaano8.closedpull.repository

import com.kaano8.closedpull.api.GithubService
import com.kaano8.closedpull.data.ClosedPrDataModel
import com.kaano8.closedpull.data.REPO_NAME
import com.kaano8.closedpull.data.USERNAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClosedPrRepositoryImpl(private val service: GithubService) : ClosedPrRepository {

    override suspend fun getClosedPrs(): List<ClosedPrDataModel> =
        withContext(Dispatchers.IO) {
            service.getClosedPrs(USERNAME, REPO_NAME)
        }
}
