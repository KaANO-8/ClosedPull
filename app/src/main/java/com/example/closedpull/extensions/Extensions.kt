package com.example.closedpull.extensions

import com.example.closedpull.data.ClosedPrDataModel
import com.example.closedpull.ui.main.adapter.data.ClosedPrUiModel

fun ClosedPrDataModel.mapToUiModel(): ClosedPrUiModel {
    return ClosedPrUiModel(
        id = id,
        title = title,
        username = user.login,
        // format date
        creationDate = createdAt,
        closedDate = closedAt,
        imageUrl = user.avatarUrl
    )
}
