package com.kaano8.closedpull.api

import com.kaano8.closedpull.api.data.ClosedPrDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubService {

    @GET("/repos/{owner}/{repoName}/pulls")
    suspend fun getClosedPrs(
        @Path("owner") owner: String,
        @Path("repoName") repoName: String,
        @Query("state") state: String = "closed",
        // kept this for demo purpose
        @Query("per_page") perPage: Int = 2,
        @Query("page") page: Int
    ): List<ClosedPrDataModel>
}
