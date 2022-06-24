package com.example.closedpull.api

import retrofit2.http.GET


interface GithubService {

    @GET
    suspend fun getClosedPrs()
}