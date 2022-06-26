package com.kaano8.closedpull.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaano8.closedpull.api.data.ClosedPrException
import com.kaano8.closedpull.extensions.mapToUiModel
import com.kaano8.closedpull.repository.ClosedPrRepository
import com.kaano8.closedpull.ui.main.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ClosedPrViewModel @Inject constructor(private val repository: ClosedPrRepository) :
    ViewModel() {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState>
        get() = _uiState

    fun getClosedPrs() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = repository.getClosedPrs()
                _uiState.value = if (response.isEmpty())
                    UiState.NoClosedPrs
                else
                    UiState.Success(closedPrList = response.map { it.mapToUiModel() })
            } catch (exception: ClosedPrException) {
                _uiState.value = UiState.Error(exception.displayMessage)
            }
        }
    }
}
