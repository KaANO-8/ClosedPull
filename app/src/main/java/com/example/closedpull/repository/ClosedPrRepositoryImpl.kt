package com.example.closedpull.repository

import com.example.closedpull.api.GithubService
import com.example.closedpull.data.ClosedPrDataModel
import com.example.closedpull.data.REPO_NAME
import com.example.closedpull.data.USERNAME

class ClosedPrRepositoryImpl(private val service: GithubService) : ClosedPrRepository {

    override suspend fun getClosedPrs(): List<ClosedPrDataModel> =
        service.getClosedPrs(USERNAME, REPO_NAME)

}
