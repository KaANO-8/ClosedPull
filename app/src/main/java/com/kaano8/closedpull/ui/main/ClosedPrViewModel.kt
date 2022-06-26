package com.kaano8.closedpull.ui.main

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.kaano8.closedpull.api.GithubService
import com.kaano8.closedpull.extensions.mapToUiModel
import com.kaano8.closedpull.repository.GithubPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ClosedPrViewModel @Inject constructor(private val service: GithubService) :
    ViewModel() {

    val flow = Pager(
        config = PagingConfig(pageSize = 3),
        // We've to always provide a new instance of paging source,
        // for events like refresh()
        pagingSourceFactory = { GithubPagingSource(service) })
        .flow
        .map { pagingData ->
            pagingData.map { closedPrDataModel ->
                closedPrDataModel.mapToUiModel()
            }
        }
        .cachedIn(viewModelScope)

}
