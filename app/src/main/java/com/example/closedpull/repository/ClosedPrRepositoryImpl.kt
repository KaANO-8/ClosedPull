package com.example.closedpull.repository

import com.example.closedpull.api.GithubService
import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel

class ClosedPrRepositoryImpl(private val service: GithubService): ClosedPrRepository {

    override fun getClosedPrs(): List<ClosedPrUiModel> =
        listOf(
            ClosedPrUiModel(id = "1", username = "KaANO", title = "Test", creationDate = "20/02/2022", closedDate = "21/02/2022"),
            ClosedPrUiModel(id = "1", username = "KaANO", title = "Test", creationDate = "20/02/2022", closedDate = "21/02/2022"),
            ClosedPrUiModel(id = "1", username = "KaANO", title = "Test", creationDate = "20/02/2022", closedDate = "21/02/2022")
        )
}