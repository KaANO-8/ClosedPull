package com.kaano8.closedpull.api.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @Keep will help preserve data models once proguard is enabled.
 */
@Keep
data class ClosedPrDataModel(
    val id: Int,
    val title: String,
    val state: String,
    val user: User,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("closed_at") val closedAt: String
)

/**
 * @Keep will help preserve data models once proguard is enabled.
 */
@Keep
data class User(
    @SerializedName("avatar_url") val avatarUrl: String,
    val title: String,
    val login: String
)

