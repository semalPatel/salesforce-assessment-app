package com.salesforce.mivi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salesforce.mivi.Result
import com.salesforce.mivi.data.MediaEntity
import com.salesforce.mivi.data.MediaEntityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchContentViewModel @Inject constructor(
    private val repository: ContentRepository
) : ViewModel() {

    val contentResult: MutableLiveData<Result<MediaEntityList>> = MutableLiveData()
    val singleContentResult: MutableLiveData<Result<MediaEntity>> = MutableLiveData()
    val uiState: MutableLiveData<UiState> = MutableLiveData()

    fun searchContent(query: String) {
        viewModelScope.launch {
            updateUiState(UiState.LOADING)
            when(val result = repository.getContentList(query)) {
                is Result.Success -> {
                    updateUiState(UiState.SUCCESSFUL)
                    contentResult.postValue(result)
                }
                is Result.Failure -> {
                    updateUiState(UiState.FAILED)
                    contentResult.postValue(result)
                }
            }
        }
    }

    fun searchContentById(id: String) {
        viewModelScope.launch {
            updateUiState(UiState.LOADING)
            when(val result = repository.getContentById(id)) {
                is Result.Success -> {
                    updateUiState(UiState.SUCCESSFUL)
                    singleContentResult.postValue(result)
                }
                is Result.Failure -> {
                    updateUiState(UiState.FAILED)
                    singleContentResult.postValue(result)
                }
            }
        }
    }

    private fun updateUiState(newState: UiState) = uiState.postValue(newState)
}

enum class UiState {
    LOADING, SUCCESSFUL, FAILED
}