package com.kaano8.closedpull.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaano8.closedpull.api.Constants.REPO_NAME
import com.kaano8.closedpull.api.Constants.USERNAME
import com.kaano8.closedpull.api.GithubService
import com.kaano8.closedpull.api.data.ClosedPrDataModel
import retrofit2.HttpException
import java.io.IOException

class GithubPagingSource(private val githubService: GithubService) :
    PagingSource<Int, ClosedPrDataModel>() {

    override fun getRefreshKey(state: PagingState<Int, ClosedPrDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClosedPrDataModel> {

        return try {
            val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
            val response = githubService.getClosedPrs(USERNAME, REPO_NAME, page = position)

            LoadResult.Page(
                data = response,
                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val GITHUB_STARTING_PAGE_INDEX = 1
    }
}
