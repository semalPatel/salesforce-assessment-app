package com.salesforce.mivi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salesforce.mivi.data.Result
import com.salesforce.mivi.data.MediaEntityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    val contentResult: MutableLiveData<Result<MediaEntityList>> = MutableLiveData()
    val uiState: MutableLiveData<UiState> = MutableLiveData()

    fun searchContent(query: String) {
        viewModelScope.launch {
            updateUiState(UiState.LOADING)
            when (val result = contentRepository.getContentList(query)) {
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

    private fun updateUiState(newState: UiState) = uiState.postValue(newState)
}

enum class UiState {
    LOADING, SUCCESSFUL, FAILED
}