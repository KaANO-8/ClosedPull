package com.kaano8.closedpull.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ClosedPrDataModel(
    val id: Int,
    val title: String,
    val state: String,
    val user: User,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("closed_at") val closedAt: String
)

@Keep
data class User(
    @SerializedName("avatar_url") val avatarUrl: String,
    val title: String,
    val login: String
)

const val USERNAME = "KaANO-8"
const val REPO_NAME = "ClosedPull"