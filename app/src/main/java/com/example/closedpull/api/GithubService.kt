package com.example.closedpull.api

import com.example.closedpull.data.ClosedPrDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubService {

    @GET("/repos/{owner}/{repoName}/pulls")
    suspend fun getClosedPrs(
        @Path("owner") owner: String,
        @Path("repoName") repoName: String,
        @Query("state") state: String = "closed"
        //@Query("page") nextPageNumber: Int
    ): List<ClosedPrDataModel>
}
