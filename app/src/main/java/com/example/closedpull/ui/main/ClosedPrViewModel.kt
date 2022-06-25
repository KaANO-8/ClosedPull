package com.example.closedpull.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.closedpull.extensions.mapToUiModel
import com.example.closedpull.repository.ClosedPrRepository
import com.example.closedpull.ui.main.state.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ClosedPrViewModel @Inject constructor(private val repository: ClosedPrRepository) :
    ViewModel() {

    fun getClosedPrs() = liveData(Dispatchers.IO) {
        emit(UiStatus.Loading)
        try {
            val response = repository.getClosedPrs()
            emit(UiStatus.Success(closedPrList = response.map { it.mapToUiModel() }))

        } catch (exception: Exception) {
            emit(UiStatus.Error(exception.localizedMessage ?: "Generic error"))
        }
    }
}
