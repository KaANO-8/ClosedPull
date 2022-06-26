package com.kaano8.closedpull.extensions

import com.kaano8.closedpull.api.data.ClosedPrDataModel
import com.kaano8.closedpull.ui.main.adapter.data.ClosedPrUiModel

fun ClosedPrDataModel.mapToUiModel(): ClosedPrUiModel {
    return ClosedPrUiModel(
        id = id,
        title = title,
        username = user.login,
        creationDate = createdAt,
        closedDate = closedAt,
        imageUrl = user.avatarUrl
    )
}
